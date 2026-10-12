package pe.edu.upc.talenttrack.features.employees.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import pe.edu.upc.talenttrack.features.employees.domain.Employee

/**
 * Modal to edit position, department, salary, email and phone of a saved employee.
 * The salary must be a valid number, otherwise "Save" stays disabled.
 */
@Composable
fun EditEmployeeDialog(
    employee: Employee,
    onDismiss: () -> Unit,
    onConfirm: (Employee) -> Unit
) {
    var position by remember(employee.id) { mutableStateOf(employee.position) }
    var department by remember(employee.id) { mutableStateOf(employee.department) }
    var salary by remember(employee.id) { mutableStateOf(employee.salary.toString()) }
    var email by remember(employee.id) { mutableStateOf(employee.email) }
    var phone by remember(employee.id) { mutableStateOf(employee.phone) }

    val parsedSalary = salary.toDoubleOrNull()
    val isValid = parsedSalary != null && parsedSalary >= 0 && position.isNotBlank()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit ${employee.fullName}") },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = position,
                    onValueChange = { position = it },
                    label = { Text("Position") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = department,
                    onValueChange = { department = it },
                    label = { Text("Department") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = salary,
                    onValueChange = { salary = it },
                    label = { Text("Salary") },
                    isError = parsedSalary == null,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )
            }
        },
        confirmButton = {
            TextButton(
                enabled = isValid,
                onClick = {
                    onConfirm(
                        employee.copy(
                            position = position.trim(),
                            department = department.trim(),
                            salary = parsedSalary ?: employee.salary,
                            email = email.trim(),
                            phone = phone.trim()
                        )
                    )
                }
            ) { Text("Save") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}