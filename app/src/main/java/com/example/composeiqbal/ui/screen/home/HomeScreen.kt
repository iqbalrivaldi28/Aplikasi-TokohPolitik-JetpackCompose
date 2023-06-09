package com.example.composeiqbal.ui.screen.home


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeiqbal.R
import com.example.composeiqbal.di.Injection
import com.example.composeiqbal.models.TokohPolitik
import com.example.composeiqbal.ui.UiState
import com.example.composeiqbal.ui.components.EmptyList
import com.example.composeiqbal.ui.components.SearchBar
import com.example.composeiqbal.ui.components.TokohPolitikListItem
import com.example.composeiqbal.ui.screen.ViewModelFactory

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Int) -> Unit,
) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.search(query)
            }
            is UiState.Success -> {
                HomeContent(
                    query = query,
                    onQueryChange = viewModel::search,
                    listTokoh = uiState.data,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    query: String,
    onQueryChange: (String) -> Unit,
    listTokoh: List<TokohPolitik>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
) {
    Column {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange
        )
        if (listTokoh.isNotEmpty()) {
            ListTokoh(
                listTokoh = listTokoh,
                navigateToDetail = navigateToDetail
            )
        } else {
            EmptyList(
                Warning = stringResource(R.string.empty_data),
                modifier = Modifier
                    .testTag("emptyList")
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListTokoh(
    listTokoh: List<TokohPolitik>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    contentPaddingTop: Dp = 0.dp,
) {
    LazyColumn(
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp,
            top = contentPaddingTop
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .testTag("lazy_list")
    ) {
        items(listTokoh, key = { it.id }) { item ->
            TokohPolitikListItem(
                id = item.id,
                nama = item.nama,
                negara = item.negara,
                gambar = item.gambar,
                modifier = Modifier
                    .clickable { navigateToDetail(item.id) }
            )
        }
    }
}
