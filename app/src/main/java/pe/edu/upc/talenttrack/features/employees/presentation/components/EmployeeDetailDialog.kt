package pe.edu.upc.talenttrack.features.employees.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pe.edu.upc.talenttrack.core.util.toCurrency
import pe.edu.upc.talenttrack.features.employees.domain.Employee

/**
 * Modal that shows every field of an employee returned by the API.
 */
@Composable
fun EmployeeDetailDialog(
    employee: Employee,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = employee.fullName) },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    model = employee.photoUrl,
                    contentDescription = employee.fullName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                LabeledValue("ID", employee.id)
                LabeledValue("Age", employee.age.toString())
                LabeledValue("Profession", employee.profession)
                LabeledValue("Position", employee.position)
                LabeledValue("Experience", "${employee.experience} years")
                LabeledValue("Department", employee.department)
                LabeledValue("Salary", employee.salary.toCurrency())
                LabeledValue("Email", employee.email)
                LabeledValue("Phone", employee.phone)
                LabeledValue("Address", employee.address)
                LabeledValue("District", employee.district)
                LabeledValue("City", employee.city)
                LabeledValue("Country", employee.country)
                LabeledValue("Marital status", employee.maritalStatus)
                LabeledValue("Gender", employee.gender)
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Close") }
        }
    )
}

@Composable
fun LabeledValue(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(vertical = 2.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value.ifBlank { "-" },
            style = MaterialTheme.typography.bodyMedium
        )
    }
}