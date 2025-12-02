package Presentation.create_account


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.habittracker.di.AppModule
import com.example.habittracker.presentation.create_account.CreateAccountViewModel

class CreateAccountViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateAccountViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CreateAccountViewModel(
                AppModule.provideUserRepository(context)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

