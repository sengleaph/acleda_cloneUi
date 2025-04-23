    package com.sifu.trainingui.Views.TransferPage

    import androidx.compose.foundation.Image
    import androidx.compose.foundation.background
    import androidx.compose.foundation.border
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.IntrinsicSize
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.offset
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.layout.width
    import androidx.compose.foundation.layout.wrapContentHeight
    import androidx.compose.foundation.rememberScrollState
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.foundation.text.KeyboardOptions
    import androidx.compose.foundation.verticalScroll
    import androidx.compose.material.ExperimentalMaterialApi
    import androidx.compose.material.IconButton
    import androidx.compose.material.ModalBottomSheetLayout
    import androidx.compose.material.ModalBottomSheetValue
    import androidx.compose.material.Scaffold
    import androidx.compose.material.Text
    import androidx.compose.material.TopAppBar
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.ArrowBack
    import androidx.compose.material.icons.filled.ArrowDropDown
    import androidx.compose.material.icons.outlined.Favorite
    import androidx.compose.material.rememberModalBottomSheetState
    import androidx.compose.material3.Divider
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.Icon
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.OutlinedTextField
    import androidx.compose.material3.Switch
    import androidx.compose.material3.SwitchDefaults
    import androidx.compose.material3.TextFieldDefaults
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.rememberCoroutineScope
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.paint
    import androidx.compose.ui.graphics.Brush
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.AnnotatedString
    import androidx.compose.ui.text.TextStyle
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.input.KeyboardType
    import androidx.compose.ui.text.input.OffsetMapping
    import androidx.compose.ui.text.input.TransformedText
    import androidx.compose.ui.text.input.VisualTransformation
    import androidx.compose.ui.text.style.TextAlign
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.navigation.NavHostController
    import com.sifu.trainingui.R
    import com.sifu.trainingui.Views.FavoritesPage.FavoritesScreen
    import com.sifu.trainingui.components.utils.FavoriteUserComponent
    import com.sifu.trainingui.components.utils.PurposeComponent
    import com.sifu.trainingui.components.utils.SheetType
    import com.sifu.trainingui.components.utils.WalletAmount
    import com.sifu.trainingui.components.utils.navigation_bar.Screen
    import com.sifu.trainingui.model.FavoriteUserModel
    import com.sifu.trainingui.model.PurposeModel
    import com.sifu.trainingui.model.WalletModel
    import com.sifu.trainingui.ui.theme.AmberColor
    import com.sifu.trainingui.ui.theme.Navy
    import kotlinx.coroutines.launch

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalMaterialApi::class
    )
    @Composable
    fun TransferScreen(
    navController: NavHostController,
    ) {
    var text by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }
    var selectedPurpose by remember { mutableStateOf<String?>(null) }
    var saveFavorite by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()
    var toAccount by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var showAccountInvalid by remember { mutableStateOf(false) }
    var showAmountInvalid  by remember { mutableStateOf(false) }
    var currentSheet by remember { mutableStateOf<SheetType?>(null) }

        Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background),
                alignment = Alignment.TopCenter,
                contentScale = ContentScale.Crop,
            )
    ) {
            ModalBottomSheetLayout(
                sheetState     = sheetState,
                sheetShape     = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                sheetElevation = 8.dp,
                sheetContent   = {
                    when (currentSheet) {
                        SheetType.Wallet -> {
                            Column(Modifier.height(250.dp)) {
                                // Header bar
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(70.dp)
                                        .background(
                                            Brush.verticalGradient(
                                                listOf(AmberColor, Color.Black.copy(alpha = 0.9f))
                                            )
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        "Favorite",
                                        style = TextStyle(
                                            Color.White,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.W400
                                        )
                                    )
                                }
                                WalletSelectorScreen()
                            }
                        }

                        SheetType.Favorite -> {
                            Column(Modifier.height(500.dp)) {
                                // Header bar
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(70.dp)
                                        .background(
                                            Brush.verticalGradient(
                                                listOf(AmberColor, Color.Black.copy(alpha = 0.9f))
                                            )
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        "Select Account",
                                        style = TextStyle(
                                            Color.White,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.W400
                                        )
                                    )
                                }
                                FavoriteUserScreen { phone ->
                                    text = phone
                                    scope.launch { sheetState.hide() }
                                    currentSheet = null
                                }
                            }
                        }

                        SheetType.Purpose ->
                            Column {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(70.dp)
                                        .background(
                                            Brush.verticalGradient(
                                                listOf(AmberColor, Color.Black.copy(alpha = 0.9f))
                                            )
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        "Purpose",
                                        style = TextStyle(
                                            Color.White,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.W400
                                        )
                                    )
                                }
                                Column(
                                    Modifier
                                        .wrapContentHeight()
                                        .verticalScroll(rememberScrollState())
                                ) {
                                    // Header bar
                                    PurposeScreen { choice ->
                                        selectedPurpose = choice
                                        scope.launch { sheetState.hide() }
                                        currentSheet = null
                                    }
                                }
                            }
                        null -> Unit
                    }
                }
            )  {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                backgroundColor = Color.Transparent,
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "My KHQR",
                                style = TextStyle(
                                    Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.W400
                                )
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.Home.route) {
                                        inclusive = true
                                    }
                                }
                            }
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .size(30.dp),
                                    tint = Color.White,
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { /* doSomething() */ }) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_acleda_icon),
                                    contentDescription = "ACLEDA Icon",
                                    modifier = Modifier
                                        .size(60.dp)
                                        .padding(end = 20.dp)
                                )
                            }
                        },
                        backgroundColor = Color.White.copy(alpha = 0.0f),
                        elevation = 0.dp
                    )
                },
                content = { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxWidth()
                                .align(Alignment.Center)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.im_acleda_icon),
                                    contentDescription = "ACLEDA Icon",
                                    modifier = Modifier
                                        .size(80.dp)
                                        .border(
                                            width = 1.dp,
                                            color = Color.White,
                                            shape = RoundedCornerShape(20)
                                        )
                                        .align(Alignment.CenterHorizontally)
                                )
                                Text(
                                    text = "Acleda Account | Phone Numbers",
                                    style = TextStyle(
                                        Color.White,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.W400
                                    ),
                                    modifier = Modifier
                                        .padding(
                                            vertical = 10.dp,
                                        )
                                        .align(Alignment.CenterHorizontally)
                                )
                                Spacer(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .size(1.dp)
                                        //                            .padding(16.dp)
                                        .align(Alignment.CenterHorizontally)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        Color.White,
                                        shape = RoundedCornerShape(
                                            topStart = 20.dp,
                                            topEnd = 20.dp,
                                        )
                                    )
                            ) {
                                // the white card
                                Column {
                                    Box(
                                        modifier = Modifier
                                            .padding(top = 16.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(
                                                    start = 16.dp,
                                                    end = 16.dp,
                                                    top = 16.dp,
                                                )
                                                .background(
                                                    color = Color.White,
                                                    shape = RoundedCornerShape(20.dp)
                                                )
                                                .border(
                                                    width = 1.dp,
                                                    color = Color.Black.copy(alpha = 0.1f),
                                                    shape = RoundedCornerShape(20.dp)
                                                )
                                                .clickable(onClick = {}),
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Center
                                            ) {
                                                Column(
                                                    modifier = Modifier
                                                        .weight(1f),
                                                    horizontalAlignment = Alignment.End
                                                ) {
                                                    Text(
                                                        text = "017 350 216",
                                                        style = TextStyle(
                                                            color = Color.Gray,
                                                            fontSize = 16.sp,
                                                            fontWeight = FontWeight.W400
                                                        ),
                                                    )
                                                    Spacer(Modifier.height(2.dp))
                                                    Text(
                                                        text = "45.44 USD",
                                                        style = TextStyle(
                                                            color = Navy,
                                                            fontSize = 16.sp,
                                                            fontWeight = FontWeight.W400
                                                        ),
                                                    )
                                                }

                                                Spacer(modifier = Modifier.width(16.dp))
                                                Icon(
                                                    imageVector = Icons.Filled.ArrowDropDown,
                                                    contentDescription = "Select account",
                                                    tint = AmberColor,
                                                    modifier = Modifier
                                                        .size(50.dp)
                                                        .background(
                                                            color = Color.Gray.copy(alpha = 0.1f),
                                                            shape = RoundedCornerShape(10.dp)
                                                        )
                                                        .clickable {
                                                            currentSheet = SheetType.Wallet
                                                            scope.launch {
                                                                sheetState.show()   // animate sheet up
                                                            }
                                                        }
                                                )
                                            }
                                        }
                                        Text(
                                            text = "From Account",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = Color.Gray,
                                            modifier = Modifier
                                                .padding(
                                                    start = 31.dp,
                                                    top = 16.dp,
                                                )
                                                .background(
                                                    color = Color.White,
                                                )
                                                .width(100.dp)
                                                .offset(y = (-10).dp)
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp, vertical = 32.dp)
                                    ) {
                                        // Main row: input field + favorite icon
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(IntrinsicSize.Min),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            // 1) Input “card” as a read‑only styled TextField
                                            OutlinedTextField(
                                                value = toAccount,
                                                onValueChange = { entered ->
                                                    // strip non-digits & validate
                                                    val digits = entered.filter { it.isDigit() }
                                                    showAccountInvalid = digits.toIntOrNull() == null && digits.isNotEmpty()
                                                    toAccount = digits
                                                },
                                                label = { Text("To Account", style = TextStyle(
                                                    color = Color.Gray,
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.W400
                                                )) },
                                                singleLine = true,
                                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                                textStyle = TextStyle(
                                                    color = Navy,
                                                    fontSize = 18.sp,
                                                    fontWeight = FontWeight.W400
                                                ),
                                                visualTransformation = PhoneNumberVisualTransformation(),
                                                placeholder = { /* optional */ },
                                                trailingIcon = {
                                                    Box(
                                                        modifier = Modifier
                                                            .padding(end = 16.dp)
                                                            .background(
                                                                color = Color.Gray.copy(alpha = 0.1f),
                                                                shape = RoundedCornerShape(10.dp)
                                                            )
                                                            .size(40.dp)
                                                            .clickable { /* scan */ },
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        Icon(
                                                            painter = painterResource(id = R.drawable.ic_scan_qr),
                                                            contentDescription = "Scan QR",
                                                            tint = AmberColor,
                                                            modifier = Modifier.size(24.dp)
                                                        )
                                                    }
                                                },
                                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                                    focusedBorderColor = Navy,
                                                    unfocusedBorderColor = Color.Black.copy(alpha = 0.1f),
                                                    containerColor = Color.White
                                                ),
                                                shape = RoundedCornerShape(20.dp),
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .height(70.dp)
                                                    .padding(end = 8.dp)
                                                    .clickable { /* show picker */ }
                                            )

                                            // 2) Favorite button
                                            IconButton(
                                                onClick = {
                                                    currentSheet = SheetType.Favorite
                                                    scope.launch { sheetState.show() }
                                                },
                                                modifier = Modifier
                                                    .size(50.dp)
                                                    .background(
                                                        color = Color.Gray.copy(alpha = 0.1f),
                                                        shape = RoundedCornerShape(10.dp)
                                                    ),
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Outlined.Favorite,
                                                    contentDescription = "Favorite",
                                                    tint = AmberColor,
                                                    modifier = Modifier.size(24.dp)
                                                )
                                            }
                                        }
                                    }
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        OutlinedTextField(
                                            label = {
                                                Text(
                                                    text = "Amount",
                                                    style = TextStyle(
                                                        color = Color.Gray,
                                                        fontSize = 16.sp,
                                                        fontWeight = FontWeight.W400
                                                    )
                                                )
                                            },
                                            value = amount,
                                            onValueChange = { entered ->
                                                val digits = entered.filter { it.isDigit() }
                                                if (digits.isNotEmpty()) {
                                                    showAmountInvalid = try {
                                                        digits.toInt(); false
                                                    } catch (_: NumberFormatException) {
                                                        true
                                                    }
                                                }
                                                amount = digits
                                            },
                                            singleLine = true,
                                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),   // ⬅ numeric keyboard
                                            textStyle = TextStyle(
                                                color = Navy,
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.W400,
                                                lineHeight = 70.sp
                                            ),
                                            trailingIcon = {
                                                Box(
                                                    modifier = Modifier
                                                        .padding(end = 16.dp)
                                                        .background(
                                                            color = Color.Gray.copy(alpha = 0.1f),
                                                            shape = RoundedCornerShape(10.dp)
                                                        )
                                                        .size(60.dp)
                                                        .clickable { /* scan */ },
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Icon(
                                                        painter = painterResource(id = R.drawable.ic_dollar_cash),
                                                        contentDescription = "Scan QR",
                                                        tint = AmberColor,
                                                        modifier = Modifier.size(32.dp)
                                                    )
                                                }
                                            },
                                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                                focusedBorderColor = Navy,
                                                unfocusedBorderColor = Color.Black.copy(alpha = 0.1f),
                                                containerColor = Color.White
                                            ),
                                            shape = RoundedCornerShape(20.dp),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(100.dp)
                                                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp, vertical = 16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .width(150.dp)
                                                ) {
                                            Text(
                                                text = "Purpose"
                                            )
                                        }
                                        Box(
                                            modifier = Modifier
                                                .width(150.dp)
                                        ) {
                                            Text(
                                                selectedPurpose ?: "",
                                                modifier = Modifier.padding(end = 8.dp)
                                            )
                                        }
                                        Spacer(modifier = Modifier.weight(1f))
                                        Switch(
                                            checked = checked,
                                            onCheckedChange = { isOn ->
                                                checked = isOn
                                                if (isOn) {
                                                    currentSheet = SheetType.Purpose       // ← set it here
                                                    scope.launch { sheetState.show() }
                                                } else {
                                                    selectedPurpose = null
                                                    currentSheet = null
                                                }
                                            },
                                            colors = SwitchDefaults.colors(
                                                checkedThumbColor   = AmberColor,
                                                uncheckedThumbColor = Color.Gray,
                                                checkedTrackColor   = AmberColor.copy(alpha = 0.3f),
                                                uncheckedTrackColor = Color.Gray.copy(alpha = 0.3f)
                                            )
                                        )

                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 16.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Text(
                                            text = "Save to Favorite",
                                        )
                                        Spacer(modifier = Modifier.weight(1f))
                                        Switch(
                                            modifier = Modifier,
                                            checked = saveFavorite,
                                            onCheckedChange = {
                                                saveFavorite = it
                                            },
                                            colors = SwitchDefaults.colors(
                                                checkedThumbColor = AmberColor,
                                                uncheckedThumbColor = Color.Gray,
                                                checkedTrackColor = AmberColor.copy(alpha = 0.3f),
                                                uncheckedTrackColor = Color.Gray.copy(alpha = 0.3f)
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                },
                bottomBar = {
                    Box(
                        modifier = Modifier
                            .background(
                                color = AmberColor
                            )
                            .clickable {
                                navController.navigate(Screen.ConfirmTransfer.route) {
                                    popUpTo(Screen.ConfirmTransfer.route) {
                                        inclusive = true
                                    }
                                }
                            }
                            .height(50.dp),
                    ) {
                        Text(
                            text = "Transfer",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W400,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 12.dp)
                        )
                    }
                }
            )
        }
        }
    }

    // 4️⃣ Usage in your screen/composable
    @Composable
    fun WalletSelectorScreen() {
        var selected by remember { mutableStateOf(0) }
        val wallets = WalletModel

        Column(modifier = Modifier.fillMaxWidth()) {
            wallets.forEachIndexed { index, wallet ->
                WalletAmount(
                    phoneNumber = wallet.phoneNumber,
                    subTitle = wallet.subTitle,
                    currency = wallet.currency.toString(),
                    amount = wallet.amount,
                    onSelect = {
                        // update selection and delegate to the model’s callback
                        selected = index
                        wallet.onSelect()
                    }
                )
                // optional divider between rows
                if (index < wallets.lastIndex) {
                    Divider(
                        color = Color.Black.copy(alpha = 0.1f),
                        thickness = 5.dp
                    )
                }
            }
        }
    }

    @Composable
    fun FavoriteUserScreen(onSelect: (String) -> Unit) {
        var selected by remember { mutableStateOf<String?>(null) }
        val favoriteUsers = FavoriteUserModel

        Column {
            favoriteUsers.forEach { user ->
                FavoriteUserComponent(
                    phoneNumber = user.phoneNumber,
                    subTitle = user.subTitle,
                    currency = user.currency,
                    amount = user.amount,
                    onSelect = {
                        selected = user.phoneNumber
                        onSelect(user.phoneNumber)
                    }
                )
                Divider(
                    color = Color.Black.copy(alpha = 0.1f),
                    thickness = 5.dp
                )
            }
        }
    }

    @Composable
    fun PurposeScreen(onSelect: (String) -> Unit) {
        // Track which purpose is currently selected (by subtitle)
        var selected by remember { mutableStateOf<String?>(null) }
        val purposeList = PurposeModel

        Column {
            purposeList.forEachIndexed { index, purpose ->
                PurposeComponent(
                    // phoneNumber, currency, and amount are unused in this case,
                    // but we still pass them to satisfy the component signature:
                    phoneNumber = purpose.phoneNumber,
                    subTitle    = purpose.subTitle,
                    currency    = purpose.currency,
                    amount      = purpose.amount,
                    onSelect    = {
                        // Update local state and notify parent
                        selected = purpose.subTitle
                        onSelect(purpose.subTitle)
                    }
                )
                // Draw a divider after every item except the last
                if (index < purposeList.lastIndex) {
                    Divider(
                        color     = Color.Black.copy(alpha = 0.1f),
                        thickness = 5.dp
                    )
                }
            }
        }
    }

    class PhoneNumberVisualTransformation : VisualTransformation {
        override fun filter(text: AnnotatedString): TransformedText {
            // 1) keep only digits, max 9
            val digits = text.text.filter(Char::isDigit).take(9)

            // 2) insert spaces after index 3 and 6
            val transformed = buildString {
                for ((i, c) in digits.withIndex()) {
                    append(c)
                    if (i == 2 || i == 5) append(' ')
                }
            }

            // 3) map cursor positions
            val offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    // every block of 3 digits adds one space
                    val spacesBefore = when {
                        offset <= 3 -> 0
                        offset <= 6 -> 1
                        else         -> 2
                    }
                    return (offset + spacesBefore).coerceAtMost(transformed.length)
                }
                override fun transformedToOriginal(offset: Int): Int {
                    // subtract spaces before this position
                    // if you’re before the first space, no change;
                    // between first and second space, -1; after second, -2
                    val orig = when {
                        offset <= 3 -> offset
                        offset <= 7 -> offset - 1
                        else        -> offset - 2
                    }
                    return orig.coerceIn(0, digits.length)
                }
            }

            return TransformedText(AnnotatedString(transformed), offsetMapping)
        }
    }
