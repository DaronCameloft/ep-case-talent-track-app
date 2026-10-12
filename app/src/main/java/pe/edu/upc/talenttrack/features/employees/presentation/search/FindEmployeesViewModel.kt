package pe.edu.upc.talenttrack.features.employees.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pe.edu.upc.talenttrack.features.employees.application.GetSavedEmployeeIdsUseCase
import pe.edu.upc.talenttrack.features.employees.application.SaveEmployeeUseCase
import pe.edu.upc.talenttrack.features.employees.application.SearchEmployeesUseCase
import pe.edu.upc.talenttrack.features.employees.domain.Employee
import javax.inject.Inject

/**
 * Handles the search by profession, the detail modal and saving employees to Room.
 */
@HiltViewModel
class FindEmployeesViewModel @Inject constructor(
    private val searchEmployeesUseCase: SearchEmployeesUseCase,
    private val getSavedEmployeeIdsUseCase: GetSavedEmployeeIdsUseCase,
    private val saveEmployeeUseCase: SaveEmployeeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FindEmployeesUiState())
    val uiState: StateFlow<FindEmployeesUiState> = _uiState.asStateFlow()

    init {
        refreshSavedIds()
    }

    fun onQueryChange(newQuery: String) {
        _uiState.update { it.copy(query = newQuery) }
    }

    fun onSearchClick() {
        val profession = _uiState.value.query.trim()
        if (profession.isBlank()) {
            _uiState.update { it.copy(userMessage = "Enter a profession to search") }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            searchEmployeesUseCase(profession)
                .onSuccess { employees ->
                    _uiState.update {
                        it.copy(employees = employees, isLoading = false, hasSearched = true)
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            employees = emptyList(),
                            isLoading = false,
                            hasSearched = true,
                            errorMessage = error.message ?: "Something went wrong"
                        )
                    }
                }
        }
    }

    fun onDetailClick(employee: Employee) {
        _uiState.update { it.copy(selectedEmployee = employee) }
    }

    fun onDetailDismiss() {
        _uiState.update { it.copy(selectedEmployee = null) }
    }

    fun onSaveClick(employee: Employee) {
        if (employee.id in _uiState.value.savedIds) return
        viewModelScope.launch {
            saveEmployeeUseCase(employee)
            refreshSavedIds()
            _uiState.update { it.copy(userMessage = "${employee.fullName} saved") }
        }
    }

    fun onUserMessageShown() {
        _uiState.update { it.copy(userMessage = null) }
    }

    /** Re-reads which ids are in Room (the user may have deleted some in the other tab). */
    fun refreshSavedIds() {
        viewModelScope.launch {
            val ids = getSavedEmployeeIdsUseCase()
            _uiState.update { it.copy(savedIds = ids) }
        }
    }
}