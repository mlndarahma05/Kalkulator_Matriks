package com.example.matrikskalkulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.example.matrikskalkulator.ui.theme.MatriksKalkulatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MatriksKalkulatorTheme {
                MatriksKalkulatorApp()
            }
        }
    }
}

@Composable
fun MatriksKalkulatorApp() {
    var ordo by remember { mutableIntStateOf(2) }
    var operation by remember { mutableStateOf("Penjumlahan") }
    var matrix1 by remember {
        mutableStateOf(List(ordo) { List(ordo) { "" } })
    }
    var matrix2 by remember {
        mutableStateOf(List(ordo) { List(ordo) { "" } })
    }
    var result by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Pilih Ordo Matriks:", Modifier.padding(end = 8.dp))
                DropdownMenuBox(
                    options = listOf(2, 3, 4),
                    selectedOption = ordo,
                    onOptionSelected = {
                        ordo = it
                        matrix1 = List(ordo) { List(ordo) { "" } }
                        matrix2 = List(ordo) { List(ordo) { "" } }
                    }
                )
            }

            Spacer(Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Pilih Operasi:", Modifier.padding(end = 8.dp))
                DropdownMenuBox(
                    options = listOf("Penjumlahan", "Pengurangan", "Perkalian", "Transpose", "Determinan"),
                    selectedOption = operation,
                    onOptionSelected = { operation = it }
                )
            }

            Spacer(Modifier.height(16.dp))

            Text("Matriks 1:")
            MatrixInput(
                matrix = matrix1,
                size = ordo,
                onValueChange = { row, col, value ->
                    matrix1 = matrix1.toMutableList().apply {
                        this[row] = this[row].toMutableList().apply {
                            this[col] = value
                        }
                    }
                }
            )

            if (operation in listOf("Penjumlahan", "Pengurangan", "Perkalian")) {
                Spacer(Modifier.height(16.dp))
                Text("Matriks 2:")
                MatrixInput(
                    matrix = matrix2,
                    size = ordo,
                    onValueChange = { row, col, value ->
                        matrix2 = matrix2.toMutableList().apply {
                            this[row] = this[row].toMutableList().apply {
                                this[col] = value
                            }
                        }
                    }
                )
            }

            Spacer(Modifier.height(16.dp))

            Button(onClick = {
                result = calculateMatrix(
                    matrix1.map { it.toTypedArray() }.toTypedArray(),
                    matrix2.map { it.toTypedArray() }.toTypedArray(),
                    ordo,
                    operation
                )
            }) {
                Text("Hitung")
            }

            Spacer(Modifier.height(16.dp))


            result?.let { resultText ->
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    tonalElevation = 2.dp
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text(
                            text = resultText,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
fun MatrixInput(
    matrix: List<List<String>>,
    size: Int,
    onValueChange: (Int, Int, String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        for (i in 0 until size) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                for (j in 0 until size) {
                    OutlinedTextField(
                        value = matrix[i][j],
                        onValueChange = { newValue ->
                            if (newValue.isEmpty() || newValue.matches(Regex("^-?\\d*\\.?\\d*$"))) {
                                onValueChange(i, j, newValue)
                            }
                        },
                        modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 4.dp),
                        textStyle = MaterialTheme.typography.bodyMedium,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                            cursorColor = MaterialTheme.colorScheme.primary,
                            focusedLabelColor = MaterialTheme.colorScheme.primary
                        ),
                        placeholder = {
                            Text(
                                text = "0",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    )
                }
            }
        }
    }
}

fun validateMatrixInput(matrix: Array<Array<String>>): Boolean {
    matrix.forEach { row ->
        row.forEach { value ->
            if (value.isNotEmpty() && !value.matches(Regex("^-?\\d*\\.?\\d*$"))) {
                return false
            }
        }
    }
    return true
}

fun Array<Array<String>>.toFloatMatrix(): Array<FloatArray>? {
    return try {
        Array(this.size) { i ->
            FloatArray(this[i].size) { j ->
                this[i][j].takeIf { it.isNotEmpty() }?.toFloat() ?: 0f
            }
        }
    } catch (e: NumberFormatException) {
        null
    }
}

fun calculateMatrix(
    matrix1: Array<Array<String>>,
    matrix2: Array<Array<String>>,
    size: Int,
    operation: String
): String {
    if (!validateMatrixInput(matrix1) || !validateMatrixInput(matrix2)) {
        return "Error: Invalid input. Please enter valid numbers only."
    }

    val numMatrix1 = matrix1.toFloatMatrix() ?: return "Error: Invalid numbers in Matrix 1"
    val numMatrix2 = matrix2.toFloatMatrix() ?: return "Error: Invalid numbers in Matrix 2"

    try {
        for (i in 0 until size) {
            for (j in 0 until size) {
                numMatrix1[i][j] = matrix1[i][j].toFloatOrNull() ?: 0f
                numMatrix2[i][j] = matrix2[i][j].toFloatOrNull() ?: 0f
            }
        }

        return when (operation) {
            "Penjumlahan" -> {
                val result = Array(size) { FloatArray(size) }
                for (i in 0 until size) {
                    for (j in 0 until size) {
                        result[i][j] = numMatrix1[i][j] + numMatrix2[i][j]
                    }
                }
                "Hasil Penjumlahan:\n${result.contentDeepToString()}"
            }
            "Pengurangan" -> {
                val result = Array(size) { FloatArray(size) }
                for (i in 0 until size) {
                    for (j in 0 until size) {
                        result[i][j] = numMatrix1[i][j] - numMatrix2[i][j]
                    }
                }
                "Hasil Pengurangan:\n${result.contentDeepToString()}"
            }
            "Perkalian" -> {
                val result = Array(size) { FloatArray(size) }
                for (i in 0 until size) {
                    for (j in 0 until size) {
                        for (k in 0 until size) {
                            result[i][j] += numMatrix1[i][k] * numMatrix2[k][j]
                        }
                    }
                }
                "Hasil Perkalian:\n${result.contentDeepToString()}"
            }
            "Transpose" -> {
                val result = Array(size) { FloatArray(size) }
                for (i in 0 until size) {
                    for (j in 0 until size) {
                        result[j][i] = numMatrix1[i][j]
                    }
                }
                "Transpose Matriks:\n${result.contentDeepToString()}"
            }
            "Determinan" -> {
                try {
                    val det = calculateDeterminant(numMatrix1, size)
                    "Determinan: $det"
                } catch (e: Exception) {
                    "Error calculating determinant: ${e.message}"
                }
            }
            else -> "Operasi tidak valid."
        }
    } catch (e: Exception) {
        return "Error: ${e.message}"
    }
}

fun calculateDeterminant(matrix: Array<FloatArray>, size: Int): Float {
    return when (size) {
        2 -> calculate2x2Determinant(matrix)
        3 -> calculate3x3Determinant(matrix)
        4 -> calculate4x4Determinant(matrix)
        else -> throw IllegalArgumentException("Ukuran matriks tidak di dukung")
    }
}

fun calculate2x2Determinant(matrix: Array<FloatArray>): Float {
    return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]
}

fun calculate3x3Determinant(matrix: Array<FloatArray>): Float {
    return matrix[0][0] * (matrix[1][1] * matrix[2][2] - matrix[1][2] * matrix[2][1]) -
            matrix[0][1] * (matrix[1][0] * matrix[2][2] - matrix[1][2] * matrix[2][0]) +
            matrix[0][2] * (matrix[1][0] * matrix[2][1] - matrix[1][1] * matrix[2][0])
}

fun calculate4x4Determinant(matrix: Array<FloatArray>): Float {
    fun getMinor(matrix: Array<FloatArray>, row: Int, col: Int): Array<FloatArray> {
        val minor = Array(3) { FloatArray(3) }
        var minorRow = 0
        var minorCol: Int

        for (i in matrix.indices) {
            if (i == row) continue
            minorCol = 0
            for (j in matrix.indices) {
                if (j == col) continue
                minor[minorRow][minorCol] = matrix[i][j]
                minorCol++
            }
            minorRow++
        }
        return minor
    }

    var det = 0f
    for (i in 0..3) {
        val minor = getMinor(matrix, 0, i)
        val cofactor = if (i % 2 == 0) 1 else -1
        det += cofactor * matrix[0][i] * calculate3x3Determinant(minor)
    }
    return det
}



@Composable
fun <T> DropdownMenuBox(options: List<T>, selectedOption: T, onOptionSelected: (T) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        Button(onClick = { expanded = true }) {
            Text(selectedOption.toString())
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option.toString()) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMatriksKalkulatorApp() {
    MatriksKalkulatorTheme {
        MatriksKalkulatorApp()
    }
}