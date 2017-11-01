$(document).ready(function () {
    $('#showAll').click(function () {
        $('#makeQuizPlaceholder').hide();
        $('#loadedQuizPlaceholder').show();
        $('#loadedQuizPlaceholder').empty();
        $('#urlDiv').hide();
        $.ajax({
            type: 'GET',
            dataType: 'json',
            url: '/api/get/all',
            success: function (data) {
                data.sort(function (obj1,obj2) {
                    return obj2.id-obj1.id;
                });
                $.each(data, function (index,article) {
                    var $quizTable=$('<table class="table" align="center"></table>');
                    var $container=$('<tr></tr>');
                    $.each(article,function (key,val) {
                        if (key == 'id'){
                            var $quizId=$('<tr><td id="key">' + key + ' ' + val + '<a href="/quiz/'+val+'"> link</a></td></tr>');
                            $quizTable.append($quizId)
                        }else if (key == 'theme') {
                            var $quizAttrTr = $('<tr><td colspan="2"><p>' + val + '<p></td></tr>');
                            $quizTable.append($quizAttrTr);
                        }else if (key == 'votes') {
                            var $quizVotes= $('<tr><td>Votes: ' + val + '</td></tr>');
                            $quizTable.append($quizVotes);
                        }else {
                            if (key == 'started' && val == '1'){
                                var $quizStarted=$('<td>Was started</td>');
                                $container.append($quizStarted)
                            }
                            if (key == 'started' && val == '0'){
                                var $quizStarted=$('<td>Was not started</td>');
                                $container.append($quizStarted)
                            }
                            if (key == 'closed' && val == '1'){
                                var $quizStarted=$('<td>Is closed</td>');
                                $container.append($quizStarted)
                            }
                            if (key == 'closed' && val == '0'){
                                var $quizStarted=$('<td>Is not Closed</td>');
                                $container.append($quizStarted)
                            }
                        }
                    });
                    $quizTable.append($container);
                    $('#loadedQuizPlaceholder').append($quizTable);
                });
            }
        });
    });

    $('#makeQuiz').click(function () {
        $('#loadedQuizPlaceholder').hide();
        $('#makeQuizPlaceholder').show();
        $('#urlDiv').hide();
    });
    $('#saveQuiz').click(function () {
        var themeS = $('#theme').val();
        var j = JSON.stringify({theme: themeS});
        if (themeS.length < 20){
            $('#err').text('Too short theme!')
        }else {
            $.ajax({
                type: 'POST',
                dataType: 'json',
                data: j,
                url: '/api/makeQuiz',
                contentType: "application/json",
                success: function (data) {
                    $('#linkToQuiz').append(data.url);
                    $('#linkToQuiz').attr('href',data.url);
                    $('#urlDiv').show();
                    $('#makeQuizPlaceholder').hide();
                }
            });
        }
    });
});