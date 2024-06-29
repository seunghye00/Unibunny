var EndGameFunctions = {
    sendScore: function(score, gameId, logSeq) {
        $.ajax({
            url: '/submit.score',
            type: 'POST',
            data: {
                score: score,
                gameID: gameId,
                log_seq: logSeq
            },
            success: function(response) {
                console.log('Score submitted successfully:', response);
            },
            error: function(error) {
                console.error('Error submitting score:', error);
            }
        }).done(function(resp) {
            console.log(resp);
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
                console.log('Game log submitted successfully:', response);
                var logSeq = JSON.parse(response).log_seq; // 서버에서 받은 로그 시퀀스
                // 게임 종료 시에 점수 전송
                gameEngine.on('end', function(score) {
                    EndGameFunctions.sendScore(score, gameId, logSeq);
                });
            },
            error: function(error) {
                console.error('Error submitting game log:', error);
            }
        }).done(function(resp) {
            console.log(resp);
        });
    }
};

