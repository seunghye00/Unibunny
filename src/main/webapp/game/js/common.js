var EndGameFunctions = {
    sendScore: function(score, gameId) {
        console.log('Sending score:', score, 'for game ID:', gameId, 'with log sequence:', game.logSeq);
        $.ajax({
            url: '/submit.score',
            type: 'POST',
            data: {
                score: score,
                gameID: gameId,
                log_seq: game.logSeq // game 객체의 logSeq 속성 사용
            },
            success: function(response) {
            },
            error: function(error) {
            }
        }).done(function(resp) {
        });
    }
};

var StartGameFunctions = {
    sendGameid: function(gameId) {
        $.ajax({
            url: '/submit.gamelog',
            type: 'POST',
            data: {
                gameID: gameId
            },
            success: function(response) {
                game.logSeq = JSON.parse(response).log_seq; // 서버에서 받은 로그 시퀀스를 game 객체의 속성으로 할당
            },
            error: function(error) {
            }
        }).done(function(resp) {
        });
    }
};
