package com.hmatalonga.greenhub.ui.welcome

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hmatalonga.greenhub.R
import com.hmatalonga.greenhub.ui.theme.BatteryhubTheme
import com.hmatalonga.greenhub.ui.welcome.views.CustomButton
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

@Composable
fun WelcomeView(viewModel: WelcomeViewModel) {
    WelcomeView(viewModel.intent)
}

@Composable
private fun WelcomeView(intent: Channel<WelcomeIntent>) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.onPrimary)
    ) {
        //Header()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 30.dp)

        ) {
            TosInformation()
            Spacer(Modifier.height(70.dp))
            CustomButton(
                onClick = {
                    coroutineScope.launch {
                        intent.send(WelcomeIntent.ShowTOS)
                    }
                }, highlighted = true
            ) {
                Text(stringResource(R.string.continue_label))
            }
        }


        /*
        Box(
            Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomStart)
                .padding(bottom = 20.dp)
        ) {
            ToaActionButtons(intent)
        }

         */
    }
}

@Composable
private fun Header() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(MaterialTheme.colors.primary)

    ) {
        // TODO: change content description

        Image(
            painterResource(R.drawable.batteryhub_tos),
            "logo",
            modifier = Modifier
                .width(200.dp)
                .height(80.dp)
                .alpha(0.7f)
        )

    }
}


@Composable
private fun TosInformation() {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(MaterialTheme.colors.onPrimary)
            .scrollable(scrollState, orientation = Orientation.Vertical)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                stringResource(R.string.welcome_to_greenhub_app),
                color = MaterialTheme.colors.primary,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 30.dp)
            )
            Text(stringResource(R.string.welcome_text), fontSize = 14.sp)
            //Text(stringResource(R.string.eula_legal_text), fontSize = 14.sp)

        }
    }
}





@Preview
@Composable
fun WelcomeView_Preview() {
    BatteryhubTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            WelcomeView(intent = Channel(Channel.UNLIMITED))
        }
    }
}

