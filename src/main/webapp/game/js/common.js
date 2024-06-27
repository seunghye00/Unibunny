var EndGameFunctions = {
    sendScore: function(score,gameId) {
        $.ajax({
            url: '/submit.score', // 서버의 점수 처리 URL
            type: 'POST',
            data: {
                score: score,
                gameId: gameId,
            },
            success: function(response) {
                console.log('Score submitted successfully:', response);
            },
            error: function(error) {
                console.error('Error submitting score:', error);
            },
        }).done(function(resp) {
			console.log(resp);
        });
    }
};

var StartGameFunctions = {
    sendScore: function(gameId) {
        $.ajax({
            url: '/submit.gamelog', // 서버의 점수 처리 URL
            type: 'POST',
            data: {
                gameId: gameId
            },
            success: function(response) {
                console.log('Score submitted successfully:', response);
            },
            error: function(error) {
                console.error('Error submitting score:', error);
            },
        }).done(function(resp) {
			console.log(resp);
        });
    }
};