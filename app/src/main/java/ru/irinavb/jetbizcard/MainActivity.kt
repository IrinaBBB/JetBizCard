package ru.irinavb.jetbizcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.irinavb.jetbizcard.ui.theme.JetBizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .width(350.dp)
                .fillMaxHeight()
                .padding(12.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(
                corner = CornerSize(15.dp)
            ),
            backgroundColor = Color.White
        ) {
            Column(
                modifier = Modifier.height(350.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreateImageProfile(Modifier)
                Divider()
                CreateBio()
            }
        }
    }
}

@Composable
private fun CreateBio() {
    val buttonClickedState = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Miles P.",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant,
        )
        Text(
            text = "Android Compose Programmer",
            modifier = Modifier.padding(3.dp),
            fontSize = 18.sp
        )
        Text(
            text = "th.miles@mail.com",
            modifier = Modifier.padding(3.dp),
            fontSize = 18.sp,
        )
        Button(
            onClick = {
              buttonClickedState.value = !buttonClickedState.value
            },
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 12.dp,
                end = 20.dp,
                bottom = 12.dp
            )
        ) {
            Icon(
                Icons.Filled.ArrowForward,
                contentDescription = "Favorite",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Portfolio")
        }
        if (buttonClickedState.value) {
            Content()
        } else {
            Box {}
        }
    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(130.dp)
            .padding(20.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_man), contentDescription =
            "profile image", modifier = modifier.size(130.dp), contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun Content() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(
                width = 2.dp,
                color = Color.LightGray
            )
        ) {
            Portfolio(
                data = listOf(
                    "Project1",
                    "Project2",
                    "Project 3",
                    "Project 4"
                )
            )
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data) { item ->
            Card(
                modifier = Modifier
                    .padding(13.dp)
                    .fillMaxWidth(),
                shape = RectangleShape,
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .padding(7.dp)
                        .background(MaterialTheme.colors.surface)
                ) {
                    CreateImageProfile(
                        modifier = Modifier.size(100.dp)
                    )
                    Column(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                    ) {
                        Text(
                            text = item,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Description",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetBizCardTheme {
        CreateBizCard()
    }
}