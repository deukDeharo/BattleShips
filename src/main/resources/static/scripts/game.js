$(document).ready(function(){

  $.ajax({
    type: 'GET',
    url: "api/game_view/" + getParameterUrl()

   }).done(function(data) {
      alert( "success" );
      createGridWithLava("game","");
      createGridWithLava("oponent","alt_");
      var gameData = data;
      putBoats(gameData);
      showData(gameData);
      showJson(gameData);
      fireEveryone(gameData);
    })
    .fail(function() {
      alert( "error" );
    })
    /*.always(function() {
      alert( "complete" );
    });*/

});


function getParameterUrl(){
    var url = window.location.search;

    return url.substring(4);
}

function showData(gameData){


    $("#data").append('<p class="p_data" id="creationDate_p">Date of Creation:'+ " " + gameData.Date + '</p>');
    for(var i= 0; i<gameData.participation.length; i++){
        if(gameData.participation[i].id == getParameterUrl()){
             $("#data").append('<p class="p_data" id="player_p" > Player:'+ " " + gameData.participation[i].player.userName + "</p>");
        }else{
        $("#data").append('<p class="p_data" id="oponent_p" > Oponent:'+ " " + gameData.participation[i].player.userName + "</p>");
        }

    }

};

var json;
function showJson(gameData){
    json= gameData;
    console.log(json);
};

function fireEveryone (gameData){

    for(var i = 0; i<gameData.Salvoes.length; i++){
        if(gameData.Salvoes[i].Player == getParameterUrl()){
            fireShips(gameData,"alt_",i);
        }
        else {
            fireShips(gameData,"",i);
        }
    }

}
function fireShips(gameData,div, i){
    for (var z = 0; z<gameData.Salvoes[i].Salvos.length; z++){
        for(var q= 0; q<gameData.Salvoes[i].Salvos[z].locations.length; q++){

         $("#" + div + gameData.Salvoes[i].Salvos[z].locations[q]).empty().removeClass("tile").removeClass("lavaAnim").addClass("tileFired")
         .append("<p class='p_turnData'>"+ gameData.Salvoes[i].Salvos[z].turn+"</p>");
            if($("#" + div + gameData.Salvoes[i].Salvos[z].locations[q]).hasClass("ship")== true){
            $("#" + div + gameData.Salvoes[i].Salvos[z].locations[q]).removeClass("turned").attr("style", 'background-image: url("../assets/terrain/scope.png");')
            }
        }
    }
}