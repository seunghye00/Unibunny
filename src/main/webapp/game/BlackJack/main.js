var gameId = "4";

const config = {
  type: Phaser.AUTO,
  width: 800,
  height: 600,
  scene: {
    preload: preload,
    create: create,
    update: update
  }
}

const game = new Phaser.Game(config)

let playerFunds = 1000
let playerBet = 0
let playerCards = []
let dealerCards = []
let deck = []
let playerScore = 0
let dealerScore = 0
let betText, fundsText, playerScoreText, dealerScoreText, resultText
let betInput, betButton, hitButton, standButton, restartButton, newGameButton, increaseBetButton, decreaseBetButton
let playerCardGraphics = []
let dealerCardGraphics = []
let deckGraphics
let cardTexts = []
let dealerCardIndex = 0 // 딜러의 카드 인덱스를 추적하기 위한 변수
let standClicked = false // stand 버튼이 클릭되었는지 여부를 추적하기 위한 변수

function preload() {
  this.load.image('background', 'background.jpg')
}

function create() {
  // 배경 이미지 설정
  let background = this.add.image(0, 0, 'background')
  background.setOrigin(0, 0)
  background.setDisplaySize(this.sys.game.config.width, this.sys.game.config.height)

  betText = this.add.text(20, 20, '베팅 금액을 설정하세요', { fontSize: '24px', fill: '#fff' })
  fundsText = this.add.text(20, 60, `자금: $${playerFunds}`, { fontSize: '24px', fill: '#fff' })
  
  betInput = this.add.text(300, 20, '0', { fontSize: '24px', fill: '#fff' })
  
  increaseBetButton = this.add.text(400, 20, '+', { fontSize: '24px', fill: '#fff' }).setInteractive()
  increaseBetButton.on('pointerdown', increaseBet)
  
  decreaseBetButton = this.add.text(450, 20, '-', { fontSize: '24px', fill: '#fff' }).setInteractive()
  decreaseBetButton.on('pointerdown', decreaseBet)
  
  betButton = this.add.text(500, 20, 'Bet', { fontSize: '24px', fill: '#fff' }).setInteractive()
  betButton.on('pointerdown', () => placeBet(this))
  
  hitButton = this.add.text(50, 500, 'Hit', { fontSize: '24px', fill: '#fff' }).setInteractive()
  hitButton.on('pointerdown', () => hit(this))
  hitButton.setVisible(false)
  
  standButton = this.add.text(150, 500, 'Stand', { fontSize: '24px', fill: '#fff' }).setInteractive()
  standButton.on('pointerdown', () => {
    if (!standClicked) {
      standClicked = true
      stand(this)
    }
  })
  standButton.setVisible(false)
  
  restartButton = this.add.text(250, 500, 'Restart', { fontSize: '24px', fill: '#fff' }).setInteractive()
  restartButton.on('pointerdown', () => restartGame(this))
  restartButton.setVisible(false)
  
  newGameButton = this.add.text(350, 500, '새로운 게임', { fontSize: '24px', fill: '#fff' }).setInteractive()
  newGameButton.on('pointerdown', newGame)
  newGameButton.setVisible(false)
  
  playerScoreText = this.add.text(20, 350, '', { fontSize: '24px', fill: '#fff' })
  dealerScoreText = this.add.text(20, 150, '', { fontSize: '24px', fill: '#fff' })
  resultText = this.add.text(400, 300, '', { fontSize: '48px', fill: '#fff' }).setOrigin(0.5)
  
  // 덱 표시
  deckGraphics = this.add.graphics()
  deckGraphics.lineStyle(2, 0x000000, 1)
  deckGraphics.strokeRect(700, 50, 50, 70)
  this.add.text(710, 80, 'DECK', { fontSize: '16px', fill: '#000' })
  
  createDeck()
  shuffleDeck()
  
  StartGameFunctions.sendGameid(gameId);
}

function update() {}

function increaseBet() {
  if (playerBet + 100 <= 500 && playerBet + 100 <= playerFunds) {
    playerBet += 100
    betInput.setText(playerBet)
  }
}

function decreaseBet() {
  if (playerBet - 100 >= 0) {
    playerBet -= 100
    betInput.setText(playerBet)
  }
}

function placeBet(scene) {
  if (playerBet > playerFunds || playerBet == 0) {
    alert('유효한 베팅 금액을 입력하세요')
    return
  }
  playerFunds -= playerBet
  fundsText.setText(`자금: $${playerFunds}`)
  betButton.setVisible(false)
  betInput.setVisible(false)
  increaseBetButton.setVisible(false)
  decreaseBetButton.setVisible(false)
  betText.setText(`베팅된 금액: ${playerBet}`)
  dealCards(scene)
}

function createDeck() {
  const suits = ['hearts', 'diamonds', 'clubs', 'spades']
  const values = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A']
  deck = []
  for (let suit of suits) {
    for (let value of values) {
      deck.push({ suit, value })
    }
  }
}

function shuffleDeck() {
  for (let i = deck.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1))
    const temp = deck[i]
    deck[i] = deck[j]
    deck[j] = temp
  }
}

function dealCards(scene) {
  if (deck.length < 4) {
    endGame('NO MORE CARDS', scene)
    return
  }

  playerCards = [drawCard(), drawCard()]
  dealerCards = [drawCard(), drawCard()]
  playerScore = calculateScore(playerCards)
  dealerScore = calculateScore(dealerCards)
  
  let delay = 0
  playerCards.forEach((card, index) => {
    scene.time.addEvent({
      delay: delay,
      callback: () => displayCard(scene, card, index, 200, 400, playerCardGraphics),
      callbackScope: this,
      loop: false
    })
    delay += 500
  })

  dealerCardIndex = 0 // 딜러의 카드 인덱스 초기화
  dealerCards.forEach((card, index) => {
    scene.time.addEvent({
      delay: delay,
      callback: () => displayCard(scene, card, dealerCardIndex++, 200, 200, dealerCardGraphics, index === 1),
      callbackScope: this,
      loop: false
    })
    delay += 500
  })
  
  scene.time.addEvent({
    delay: delay,
    callback: () => {
      hitButton.setVisible(true)
      standButton.setVisible(true)
      playerScoreText.setText(`Player: ${playerScore}`)
      dealerScoreText.setText(`Dealer: ?`)
    },
    callbackScope: this,
    loop: false
  })
}

function drawCard() {
  return deck.pop()
}

function calculateScore(cards) {
  let score = 0
  let aceCount = 0
  for (let card of cards) {
    if (card.value === 'A') {
      aceCount++
      score += 11
    } else if (['J', 'Q', 'K'].includes(card.value)) {
      score += 10
    } else {
      score += parseInt(card.value)
    }
  }
  while (score > 21 && aceCount > 0) {
    score -= 10
    aceCount--
  }
  return score
}

function hit(scene) {
  if (deck.length === 0) {
    endGame('NO MORE CARDS', scene)
    return
  }
  
  const newCard = drawCard()
  playerCards.push(newCard)
  playerScore = calculateScore(playerCards)
  playerScoreText.setText(`Player: ${playerScore}`)
  displayCard(scene, newCard, playerCards.length - 1, 200, 400, playerCardGraphics)
  if (playerScore > 21) {
    endGame('LOSE', scene)
  }
}

function stand(scene) {
  dealerScore = calculateScore(dealerCards)
  dealerScoreText.setText(`Dealer: ${dealerScore}`)
  
  // 딜러의 히든 카드 공개
  displayCard(scene, dealerCards[1], 1, 200, 200, dealerCardGraphics)

  let delay = 500
  dealerCardIndex = 2 // 히든 카드 다음 인덱스로 설정
  while (dealerScore < 17) {
    if (deck.length === 0) {
      endGame('NO MORE CARDS', scene)
      return
    }

    const newCard = drawCard()
    dealerCards.push(newCard)
    dealerScore = calculateScore(dealerCards)
    scene.time.addEvent({
      delay: delay,
      callback: () => displayCard(scene, newCard, dealerCardIndex++, 200, 200, dealerCardGraphics),
      callbackScope: this,
      loop: false
    })
    delay += 500
  }

  scene.time.addEvent({
    delay: delay,
    callback: () => {
      if (dealerScore > 21 || playerScore > dealerScore) {
        endGame('WIN', scene)
      } else if (playerScore === dealerScore) {
        endGame('TIE', scene)
      } else {
        endGame('LOSE', scene)
      }
    },
    callbackScope: this,
    loop: false
  })
}

function displayCards(scene, cards, startX, startY, cardGraphics, hideSecondCard = false) {
  cards.forEach((card, index) => {
    displayCard(scene, card, index, startX, startY, cardGraphics, hideSecondCard && index === 1)
  })
}

function displayCard(scene, card, index, startX, startY, cardGraphics, hideCard = false) {
  const cardWidth = 50
  const cardHeight = 70
  const graphics = scene.add.graphics()
  graphics.lineStyle(2, 0xFFFFFF, 1)
  graphics.strokeRect(startX + index * 75, startY, cardWidth, cardHeight)
  if (!hideCard) {
    let valueText = scene.add.text(startX + index * 75 + 5, startY + 5, card.value, { fontSize: '16px', fill: '#FFFFFF' })
    let suitText = scene.add.text(startX + index * 75 + 30, startY + 50, card.suit[0].toUpperCase(), { fontSize: '16px', fill: '#FFFFFF' })
    cardTexts.push(valueText)
    cardTexts.push(suitText)
  }
  cardGraphics.push(graphics)
}

function endGame(result, scene) {
  hitButton.setVisible(false)
  standButton.setVisible(false)
  restartButton.setVisible(false)
  newGameButton.setVisible(false)

  if (result === 'WIN') {
    playerFunds += playerBet * 2
  } else if (result === 'TIE') {
    playerFunds += playerBet
  } else if (result !== 'NO MORE CARDS') {
    // No change in playerFunds if the result is 'NO MORE CARDS'
    playerFunds += 0
  }
  fundsText.setText(`자금: $${playerFunds}`)

  if (playerFunds === 0 || result === 'NO MORE CARDS') {
    if (result === 'NO MORE CARDS') {
      playerFunds += playerBet // 베팅된 금액을 다시 자금에 더해줍니다.
    }
    newGameButton.setVisible(true)
    resultText.setText(result === 'NO MORE CARDS' ? `No more cards.\n당신의 스코어는 ${playerFunds}` : 'GAME OVER')
    resultText.setFill('#fff')
    EndGameFunctions.sendScore(playerFunds, gameId);
  } else {
    restartButton.setVisible(true)
    resultText.setText(result)
    resultText.setFill(result === 'WIN' ? '#0f0' : result === 'TIE' ? '#ff0' : '#f00')
  }

  dealerScoreText.setText(`Dealer: ${dealerScore}`)
  displayCards(scene, dealerCards, 200, 200, dealerCardGraphics)
}


function restartGame(scene) {
  playerBet = 0
  playerCards = []
  dealerCards = []
  playerScore = 0
  dealerScore = 0
  playerCardGraphics.forEach(graphic => graphic.clear())
  dealerCardGraphics.forEach(graphic => graphic.clear())
  playerCardGraphics.length = 0
  dealerCardGraphics.length = 0
  cardTexts.forEach(text => text.destroy())
  cardTexts.length = 0
  betInput.setText('0')
  betButton.setVisible(true)
  betInput.setVisible(true)
  increaseBetButton.setVisible(true)
  decreaseBetButton.setVisible(true)
  restartButton.setVisible(false)
  playerScoreText.setText('')
  dealerScoreText.setText('')
  resultText.setText('')
  betText.setText('베팅 금액을 설정하세요')
  shuffleDeck()
  standClicked = false
}

function newGame() {
  location.reload()
}
