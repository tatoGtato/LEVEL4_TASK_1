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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.level4_task_1.R
import com.example.level4_task_1.data.api.Api
import com.example.level4_task_1.data.api.util.Resource
import com.example.level4_task_1.data.model.Cat
import com.example.level4_task_1.data.model.Dog
import com.example.level4_task_1.viewmodel.CatsViewModel
import com.example.level4_task_1.viewmodel.DogsViewModel

@Composable
fun DogsScreen(
    navController: NavHostController,
    viewModel: DogsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    modifier: Modifier,

    ) {
    val dogsResource: Resource<Dog>? by viewModel.dogsResource.observeAsState()


    when (dogsResource) {
        is Resource.Success -> dogsResource?.data?.randomDogPictureUrl
        is Resource.Error -> dogsResource?.message
        is Resource.Loading -> stringResource(R.string.loading_text)
        is Resource.Empty -> stringResource(R.string.emptyd)
        else -> stringResource(R.string.wrong)
    }
    val imageUrl = dogsResource?.data?.randomDogPictureUrl

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(text = stringResource(R.string.dog_tittle))
        }
        Row {
            Text(text = imageUrl?.let { getDogBreed(it) } ?: stringResource(id = R.string.emptyd))
        }
        Row {
            Image(
                painter = rememberAsyncImagePainter(dogsResource?.data?.randomDogPictureUrl ),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        }
        ExtendedFloatingActionButton(
            text = { Text(text = stringResource(R.string.click)) },
            onClick = { viewModel.getDog() },
            icon = { Icon(Icons.Filled.Refresh, "") },
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
        )
    }
}

fun getDogBreed(url:String):String{
    val fruitsArrayList = url.split("/")
    return fruitsArrayList[4]
}

