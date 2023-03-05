$(function(){
    let selectPeriod = $('#selectPeriod').val();
    let selectMonth =  $('#selectMonth').val();
    let selectTeam = $('#selectTeam').val();

    //再描画時の上書き防止
    if(selectPeriod == ''){
        selectPeriod = $("#periodId option:selected").val();
        $('#selectPeriod').val(selectPeriod);
    }else{
        $('#periodId').val(selectPeriod);
    }

    if(selectMonth == ''){
        selectMonth = $("#monthId option:selected").val();
        $('#selectMonth').val(selectMonth);
    }else{
        $('#monthId').val(selectMonth);
    }
        if(selectTeam == ''){
        selectTeam = $("#teamId option:selected").val();
        $('#selectTeam').val(selectTeam);
    }else{
        $('#teamId').val(selectTeam);
    }
    

    // 年度変更時イベント
    $('#periodId').change(function() {
        selectPeriod = $("#periodId option:selected" ).val();
        $('#selectPeriod').val(selectPeriod);
    })

    //月変更時イベント
    $('#monthId').change(function() {
        selectMonth =  $("#monthId option:selected").val();
        $('#selectMonth').val(selectMonth);
    })
    //チーム変更時イベント
    $('#teamId').change(function() {
        selectTeam =  $("#teamId option:selected").val();
        $('#selectTeam').val(selectTeam);
    })



});

