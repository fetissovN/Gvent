$(document).ready(function () {
    $('#vote').click(function () {
        var voteStatus =$('#vote').attr('class');
        var started= voteStatus.split('.')[1];
        var closed= voteStatus.split('.')[2];
        var id= voteStatus.split('.')[0];
        if (localStorage.getItem('voted') != id){
            if(closed == 0 && started == 1){
                $.ajax({
                    type: 'GET',
                    dataType: 'json',
                    url: '/api/quiz/assert/'+id,
                    success: function (data) {
                        localStorage.setItem('voted',id);
                        console.log(data.status);
                        location.reload();
                    }
                });
            }else {
                alert('is closed');
            }
        }
    });
    $('#toStart').click(function () {
        var id= $('#toStart').attr('name');
            $.getJSON('/api/start/'+id,function (data) {
                console.log(data.status);
                location.reload();
            });
    });
    $('#toClose').click(function () {
        var id= $('#toClose').attr('name');
        $.getJSON('/api/close/'+id,function (data) {
            console.log(data.status);
            location.reload();
        });
    });


});