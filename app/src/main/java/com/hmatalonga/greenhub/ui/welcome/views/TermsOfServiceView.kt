package com.hmatalonga.greenhub.ui.welcome.views


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hmatalonga.greenhub.R
import com.hmatalonga.greenhub.ui.theme.BatteryhubTheme
import com.hmatalonga.greenhub.ui.welcome.WelcomeIntent
import com.hmatalonga.greenhub.ui.welcome.WelcomeViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

@Composable
fun TermsOfServiceView(viewModel: WelcomeViewModel) {
    TermsOfServiceView(viewModel.intent)
}

@Composable
private fun TermsOfServiceView(intent: Channel<WelcomeIntent>) {
    val scrollState = rememberScrollState()
    Box {
        Column(
            Modifier
                .verticalScroll(scrollState)
                .padding(bottom = 150.dp)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(30.dp))
            // TODO: fetch tos from web
            HtmlText(
                text = "<div class=\"content\" data-v-5c6d8bbe=\"\"><h1 data-v-5c6d8bbe=\"\">GreenHub Privacy Policy</h1> <p data-v-5c6d8bbe=\"\"><strong data-v-5c6d8bbe=\"\">Last updated:</strong> 9 October 2017</p> <p data-v-5c6d8bbe=\"\">We want you to understand how and why the GreenHub platform (“GreenHub,” “we” or “us”) collects, uses, and shares information about you when you access and use GreenHub’s website, mobile app (GreenHub BatteryHub), web app (GreenHub Farmer), command-line app (GreenHub Lumberjack), and other online products and services (collectively, the \"Services\") or when you otherwise interact with us. Only the (strictly non personal) data collected via mobile app is stored in a data repository, no other service stores any information. The data that is collected by our platform is stored in a repository, unless otherwise stated.This Privacy Policy applies to all of our Services.</p> <h2 data-v-5c6d8bbe=\"\">The short version</h2> <p data-v-5c6d8bbe=\"\">We collect your information only with your consent; <strong data-v-5c6d8bbe=\"\">we DO NOT collect any personal information via our mobile app or any information unique to your mobile device such as phone number, serial or IMEI numbers;</strong> we only collect the minimum amount of personal information that is necessary to fulfill the purpose of your interaction with us when using our web app; we don't sell it to third parties; and we only use it as this Privacy Policy describes.</p> <p data-v-5c6d8bbe=\"\">Of course, the short version doesn't tell you everything, so please read on for more details!</p> <h2 data-v-5c6d8bbe=\"\">What information GreenHub collects and why</h2> <h3 data-v-5c6d8bbe=\"\">Information from the mobile app</h3> <p data-v-5c6d8bbe=\"\">If you <strong data-v-5c6d8bbe=\"\">install and accept the terms of use</strong>, we will periodically collect information regarding the energy consumption of your mobile device. The collection of information is determined by the following events:</p> <ul data-v-5c6d8bbe=\"\"><li data-v-5c6d8bbe=\"\">\n" +
                        "              Every time the mobile device battery level changes (increases or decreases).\n" +
                        "              Once one of these events take place, a sample of data will be gathered by the mobile app and stored locally on the mobile device.\n" +
                        "            </li></ul> <p data-v-5c6d8bbe=\"\">“Sample” is one instance of a collection of data that includes information such as battery current level, battery voltage, battery temperature, network connectivity details, sensors state (either if they are switched on or off), screen state (on or off), timezone, memory and CPU usage, etc.</p> <p data-v-5c6d8bbe=\"\">The samples collected via mobile app are sent automatically to our Services. The user may choose to upload the information manually or change on the mobile app preferences the interval of the automatic upload.</p> <p data-v-5c6d8bbe=\"\">Note that all data collected is purely about the device’s performance and power behavior. No personal user information is collected at any given moment. The mobile devices are identified on the Services by a randomly alphanumeric identifier used to distinguish each device in order to process the data.</p> <p data-v-5c6d8bbe=\"\">For a complete description of the data collected please refer to our documentation <a href=\"https://docs.greenhubproject.org\" data-v-5c6d8bbe=\"\">here</a>.</p> <h3 class=\"why\" data-v-5c6d8bbe=\"\">\n" +
                        "            Why do we collect this?\n" +
                        "          </h3> <ul data-v-5c6d8bbe=\"\"><li data-v-5c6d8bbe=\"\">Our strategies to analyze energy consumption behaviors rely on the use of real-world data.</li></ul> <h3 data-v-5c6d8bbe=\"\">Information from users with accounts on the web app</h3> <p data-v-5c6d8bbe=\"\">An account on the web app will give you an unique API token key to interact with our Services.</p> <p data-v-5c6d8bbe=\"\">If you <strong data-v-5c6d8bbe=\"\">create an account</strong>, we require some basic information at the time of account creation. You will provide your name, an email address and a password, and we will ask you validate your email account by sending a confirmation email to finish the registration process. </p> <p data-v-5c6d8bbe=\"\">The “User Personal Information” <strong data-v-5c6d8bbe=\"\">will not be used anywhere else except on the web app</strong> with the purpose of authenticating the user. This information will not be shared on any other of the Services.</p> <p data-v-5c6d8bbe=\"\">\"User Personal Information\" is any information about one of our users which could, alone or together with other information, personally identify him or her. Information such as a user name and password, an email address and a real name are examples of “User Personal Information.”</p> <p data-v-5c6d8bbe=\"\">User Personal Information does not include aggregated, non-personally identifying information. We may use aggregated, non-personally identifying information to operate, improve, and optimize our website and service.</p> <h3 class=\"why\" data-v-5c6d8bbe=\"\">\n" +
                        "            Why do we collect this?\n" +
                        "          </h3> <ul data-v-5c6d8bbe=\"\"><li data-v-5c6d8bbe=\"\">We need your User Personal Information to create your account, and to provide the Services you request.</li> <li data-v-5c6d8bbe=\"\">We use your User Personal Information, specifically your email address, to identify you on the web app.</li> <li data-v-5c6d8bbe=\"\">We will use your email address to communicate with you.</li> <li data-v-5c6d8bbe=\"\">We limit our use of your User Personal Information to the purposes listed in this Privacy Statement. If we need to use your User Personal Information for other purposes, we will ask your permission first.</li></ul> <h3 data-v-5c6d8bbe=\"\">Information from website browsers</h3> <p data-v-5c6d8bbe=\"\">If you're <strong data-v-5c6d8bbe=\"\">just browsing the website</strong>, we collect the same basic information that most websites collect. We use common internet technologies, such as cookies and web server logs.</p> <p data-v-5c6d8bbe=\"\">The information we collect about all visitors to our website includes the visitor’s browser type, language preference, referring site, additional websites requested, and the date and time of each visitor request. As our website uses Google Analytics, it also collects potentially personally-identifying information like Internet Protocol (IP) addresses (see below for full details).</p> <h3 class=\"why\" data-v-5c6d8bbe=\"\">\n" +
                        "            Why do we collect this?\n" +
                        "          </h3> <p data-v-5c6d8bbe=\"\">We collect this information to better understand how our website visitors use this website, and to monitor and protect the security of the website.</p> <h2 data-v-5c6d8bbe=\"\">What information GreenHub does not collect</h2> <p data-v-5c6d8bbe=\"\">We do not intentionally collect <strong data-v-5c6d8bbe=\"\">sensitive personal information</strong>, such as social security numbers, genetic data, health information, or religious information.</p> <h3 data-v-5c6d8bbe=\"\">Information from the mobile app</h3> <p data-v-5c6d8bbe=\"\"><strong data-v-5c6d8bbe=\"\">NO information that identifies or locates the mobile device is collected</strong>. Unique information of a given mobile device such as serial and IMEI numbers are not collected, <strong data-v-5c6d8bbe=\"\">only shown to the mobile app user on their mobile device</strong>. Information about exact location (such as GPS coordinates) of the mobile device is NOT collected either, only information such as timezone and service provider are collected.</p> <h2 data-v-5c6d8bbe=\"\">How we share the information we collect</h2> <p data-v-5c6d8bbe=\"\">We <strong data-v-5c6d8bbe=\"\">do not</strong> share, sell, rent, or trade User Personal Information with third parties for their commercial purposes.</p> <p data-v-5c6d8bbe=\"\">We <strong data-v-5c6d8bbe=\"\">do share all data collected from the mobile devices</strong>, the information is public. The information collected in the repository is shared through a web API.</p> <p data-v-5c6d8bbe=\"\">Our main purpose is to provide real-world data about mobile devices energy consumption. In order to allow everyone to use the data to conduct further research. We strongly suggest that the knowledge that may be produced with the use of GreenHub’s data is made public to the community.</p> <p data-v-5c6d8bbe=\"\"><strong data-v-5c6d8bbe=\"\">It is strictly FORBIDDEN TO SELL any data</strong>. This violates the purpose of this initiative. The goal is to build an open repository of information free to use. We encourage people to share any results from their research using the data collected.</p> <h2 data-v-5c6d8bbe=\"\">Links</h2> <p data-v-5c6d8bbe=\"\">The Services may contain links to other websites. We are not responsible for the privacy practices of other websites. We encourage users to be aware when they leave any Services to read the privacy statements of other websites that collect personally identifiable information. This Privacy Policy applies only to information collected by GreenHub via Services.</p> <h2 data-v-5c6d8bbe=\"\">Our use of cookies and tracking</h2> <h3 data-v-5c6d8bbe=\"\">Google Analytics</h3> <p data-v-5c6d8bbe=\"\">\n" +
                        "            We use Google Analytics as a third party tracking service, but we don’t use it to track you individually or collect your User Personal Information. We use Google Analytics to collect information about how our website performs and how our users, in general, navigate through and use GreenHub’s website. This helps us evaluate our users' use of the website; compile statistical reports on activity; and improve our content and website performance.\n" +
                        "            Google Analytics gathers certain simple, non-personally identifying information over time, such as your IP address, browser type, internet service provider, referring and exit pages, timestamp, and similar data about your use of the website.\n" +
                        "            GreenHub will not, nor will we allow any third party to, use the Google Analytics tool to track our users individually; collect any User Personal Information other than IP address; or correlate your IP address with your identity. Google provides further information about its own privacy practices and offers a <a href=\"https://tools.google.com/dlpage/gaoptout\" target=\"_blank\" data-v-5c6d8bbe=\"\">browser add-on to opt out of Google Analytics tracking</a>.\n" +
                        "            Certain pages on our site may set other third party cookies. For example, we may embed content, such as videos, from another site that sets a cookie. While we try to minimize these third party cookies, we can’t always control what cookies this third party content sets.\n" +
                        "          </p> <h3 data-v-5c6d8bbe=\"\">Do Not Track</h3> <p data-v-5c6d8bbe=\"\">The Services are not designed to respond to “do not track” signals sent by some browsers.</p> <h2 data-v-5c6d8bbe=\"\">Changes</h2> <p data-v-5c6d8bbe=\"\">We may change this Privacy Policy from time to time. If we do, we will let you know by revising the date at the top of the policy. If we make a change to this policy that, in our sole discretion, is material, we will provide you with additional notice (such as adding a statement to <a href=\"https://medium.com/greenhub-blog\" data-v-5c6d8bbe=\"\">https://medium.com/greenhub-blog</a>, the blog page of the project or sending you a notification via our mobile app GreenHub BatteryHub). We encourage you to review the Privacy Policy whenever you access or use our Services or otherwise interact with us to stay informed about our information practices and the ways you can help protect your privacy. If you continue to use our Services after Privacy Policy changes go into effect, you consent to the revised policy.</p> <h2 data-v-5c6d8bbe=\"\">Contact Us</h2> <p data-v-5c6d8bbe=\"\">If you have any questions about this Privacy Policy, please email <a href=\"mailto:greenhub@di.ubi.pt\" data-v-5c6d8bbe=\"\">greenhub@di.ubi.pt</a>.</p></div>"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colors.onPrimary

                        )
                    ), RectangleShape, 1.0f
                )
        ) {
            Spacer(Modifier.height(150.dp))
            Box(Modifier.padding(horizontal = 20.dp)){
                ActionButtons(intent)
            }
        }
    }
}

@Composable
private fun ActionButtons(intent: Channel<WelcomeIntent>) {
    val coroutineScope = rememberCoroutineScope()
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {

        Column(Modifier.weight(1f)) {
            CustomButton(
                onClick = {
                    coroutineScope.launch {
                        intent.send(WelcomeIntent.DeclineTOS)
                    }
                }
            ) {
                Text(stringResource(R.string.decline))
            }
        }
        Column(Modifier.weight(1f)) {
            CustomButton(
                onClick = {
                    coroutineScope.launch {
                        intent.send(WelcomeIntent.AcceptTOS)
                    }
                }, highlighted = false
            ) {
                Text(stringResource(R.string.accept))
            }
        }
    }
}

@Preview
@Composable
fun TermsOfServiceView_Preview() {
    BatteryhubTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxSize()
        ) {
            TermsOfServiceView(intent = Channel(Channel.UNLIMITED))
        }
    }
}
