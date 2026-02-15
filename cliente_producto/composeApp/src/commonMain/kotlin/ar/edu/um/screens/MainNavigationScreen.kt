package ar.edu.um.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ar.edu.um.screens.tabs.ProductCreateTab
import ar.edu.um.screens.tabs.ProductListTab
import ar.edu.um.screens.tabs.ProductSearchTab
import ar.edu.um.screens.tabs.ProductUpdateTab
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator

class MainNavigationScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        TabNavigator(
            tab = ProductListTab,
            tabDisposable = {
                TabDisposable(
                    navigator = it,
                    tabs = listOf(
                        ProductListTab,
                    )
                )
            }
        ){
            Scaffold(
                modifier = Modifier.safeContentPadding(),
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = it.current.options.title
                            )
                        }
                    )
                },
                bottomBar = {
                    NavigationBar {
                        val tabNavigator: TabNavigator = LocalTabNavigator.current

                        NavigationBarItem(
                            selected = tabNavigator.current.key == ProductListTab.key,
                            label = {
                                Text(
                                    text = ProductListTab.options.title
                                )
                            },
                            icon = {
                                Icon(
                                    painter = ProductListTab.options.icon!!,
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                tabNavigator.current = ProductListTab
                            }
                        )
                        NavigationBarItem(
                            selected = tabNavigator.current.key == ProductCreateTab.key,
                            label = {
                                Text(
                                    text = ProductCreateTab.options.title
                                )
                            },
                            icon = {
                                Icon(
                                    painter = ProductCreateTab.options.icon!!,
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                tabNavigator.current = ProductCreateTab
                            }
                        )

                        NavigationBarItem(
                            selected = tabNavigator.current.key == ProductUpdateTab.key,
                            label = {
                                Text(
                                    text = ProductUpdateTab.options.title
                                )
                            },
                            icon = {
                                Icon(
                                    painter = ProductUpdateTab.options.icon!!,
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                tabNavigator.current = ProductUpdateTab
                            }
                        )

                        NavigationBarItem(
                            selected = tabNavigator.current.key == ProductSearchTab.key,
                            label = {
                                Text(
                                    text = ProductSearchTab.options.title
                                )
                            },
                            icon = {
                                Icon(
                                    painter = ProductSearchTab.options.icon!!,
                                    contentDescription = null
                                )
                            },
                            onClick = {
                                tabNavigator.current = ProductSearchTab
                            }
                        )
                    }
                },
                content = { innerPadding ->
                    // Pasa el padding al contenido
                    androidx.compose.foundation.layout.Box(Modifier
                        .safeContentPadding()
                        .padding(innerPadding)
                    ) {
                        CurrentTab()
                    }
                }
            )
        }
    }
}
