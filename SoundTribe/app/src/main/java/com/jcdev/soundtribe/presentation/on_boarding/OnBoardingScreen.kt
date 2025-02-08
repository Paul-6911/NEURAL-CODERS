package com.jcdev.soundtribe.presentation.on_boarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.jcdev.soundtribe.presentation.common.ButtonBasic
import com.jcdev.soundtribe.presentation.common.ImageBasic
import com.jcdev.soundtribe.presentation.common.TextBasic
import com.jcdev.soundtribe.preview.PreviewDefault

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    val pagerState = rememberPagerState()
    val onBoardingList = getDataOnboarding()

    Column(modifier = modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .weight(10f),
            count = onBoardingList.size,
            state = pagerState
        ) { page ->
            OnBoardingContent(onBoardingPage = onBoardingList[page])
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center
        ) {
            OnBoardingFooter(pagerState = pagerState)
        }
        FinishButton(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            pagerState = pagerState,
            pages = onBoardingList,
            onClick = {
                onClick()
            }
        )
    }

}

@Composable
fun OnBoardingContent(
    modifier: Modifier = Modifier,
    onBoardingPage: OnBoardingPage
) {

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageBasic(
            image = onBoardingPage.image,
            description = "image1",
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.5f)
        )
        TextBasic(
            text = onBoardingPage.title,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        )
        TextBasic(
            text = onBoardingPage.description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp)
                .padding(24.dp),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        )

    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingFooter(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
) {
    repeat(3) { iteration ->
        val color = if (pagerState.currentPage == iteration) Color.Black else Color.Gray
        Box(
            modifier = modifier
                .padding(2.dp)
                .clip(CircleShape)
                .background(color)
                .size(12.dp)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FinishButton(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    pages:List<OnBoardingPage>,
    onClick:()->Unit
) {
    Row(modifier = modifier,
        horizontalArrangement = Arrangement.Center) {
        AnimatedVisibility(
            visible = pagerState.currentPage == pages.size - 1
        ){
            ButtonBasic(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "Finalizar",
                onClick = {
                    onClick()
                }
            )
        }
    }


}

@PreviewDefault
@Composable
fun OnBoardingScreenPreview() {
    OnBoardingScreen(onClick = {})
}