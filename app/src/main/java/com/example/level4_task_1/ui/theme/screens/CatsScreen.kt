package com.example.level4_task_1.ui.theme.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.level4_task_1.data.api.util.Resource
import com.example.level4_task_1.data.model.Cat
import com.example.level4_task_1.viewmodel.CatsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.level4_task_1.R
import com.example.level4_task_1.data.api.Api


@Composable
fun CatsScreen(
    navController: NavHostController,
    viewModel: CatsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    modifier: Modifier,

    ) {
    val catResource: Resource<Cat>? by viewModel.catsResource.observeAsState()


    when (catResource) {
        is Resource.Success -> catResource?.data?.creationDate
        is Resource.Error -> catResource?.message
        is Resource.Loading -> stringResource(R.string.loading_text)
        is Resource.Empty -> stringResource(R.string.emptyc)
        else -> stringResource(R.string.wrong)
    }
    val imageUrl = Api.CATS_BASE_URL +  catResource?.data?.urlExtension

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(text = stringResource(R.string.Cat_tittle))
        }
        Row {
            Text(text = catResource?.data?.creationDate ?: stringResource(id = R.string.emptyc))
        }
        Row {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        }
        ExtendedFloatingActionButton(
            text = { Text(text = stringResource(R.string.click)) },
            onClick = { viewModel.getCat() },
            icon = { Icon(Icons.Filled.Refresh, "") },
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
        )
    }
}


