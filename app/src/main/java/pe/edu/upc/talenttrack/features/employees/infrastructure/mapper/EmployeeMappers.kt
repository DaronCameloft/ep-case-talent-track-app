// File: features/employees/infrastructure/mapper/EmployeeMappers.kt
package pe.edu.upc.talenttrack.features.employees.infrastructure.mapper

import pe.edu.upc.talenttrack.features.employees.domain.Employee
import pe.edu.upc.talenttrack.features.employees.infrastructure.local.EmployeeEntity
import pe.edu.upc.talenttrack.features.employees.infrastructure.remote.EmployeeDto

/** API → Domain. Null-safe defaults so a partial record never crashes the UI. */
fun EmployeeDto.toDomain() = Employee(
    id = id.orEmpty(),
    firstName = firstName.orEmpty(),
    lastName = lastName.orEmpty(),
    age = age ?: 0,
    profession = profession.orEmpty(),
    position = position.orEmpty(),
    experience = experience ?: 0,
    email = email.orEmpty(),
    phone = phone.orEmpty(),
    address = address.orEmpty(),
    district = district.orEmpty(),
    city = city.orEmpty(),
    country = country.orEmpty(),
    maritalStatus = maritalStatus.orEmpty(),
    gender = gender.orEmpty(),
    salary = salary ?: 0.0,
    department = department.orEmpty(),
    photoUrl = photoUrl.orEmpty()
)

/** Domain → Room row. */
fun Employee.toEntity() = EmployeeEntity(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age,
    profession = profession,
    position = position,
    experience = experience,
    email = email,
    phone = phone,
    address = address,
    district = district,
    city = city,
    country = country,
    maritalStatus = maritalStatus,
    gender = gender,
    salary = salary,
    department = department,
    photoUrl = photoUrl
)

/** Room row → Domain. */
fun EmployeeEntity.toDomain() = Employee(
    id = id,
    firstName = firstName,
    lastName = lastName,
    age = age,
    profession = profession,
    position = position,
    experience = experience,
    email = email,
    phone = phone,
    address = address,
    district = district,
    city = city,
    country = country,
    maritalStatus = maritalStatus,
    gender = gender,
    salary = salary,
    department = department,
    photoUrl = photoUrl
)