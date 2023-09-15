package com.mylearnings.tracker_presentation.search

import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchScreen(
    snackbarHostState: SnackbarHostState,
    onNavigateUp: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    
}