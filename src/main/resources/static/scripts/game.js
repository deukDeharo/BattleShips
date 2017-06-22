$(document).ready(function(){

  $.ajax({
    type: 'GET',
    url: "api/game_view/" + getParameterUrl()

   }).done(function(data) {
      alert( "success" );
      checkBoats(data);
      var gameData = data;
      if(withBoats == true){
         $("#FirstFrame").hide();
         createGridWithLava("game","");
         createGridWithLava("oponent","alt_");
         putBoats(gameData);
         fireEveryone(gameData);
      }
      else{
      console.log("hay Barcos? "+ " "+ withBoats);
      createGridWithLava("addShipsGrid","alt2_");
      hoverEmptyBoats();
      $(".tile").click(function(){
      console.log("tilefunctionValid");
          if($(this).hasClass(chosenShip+0)){
              for(var t = 0; t<chosenShipLength; t++){
                $("."+chosenShip+t).append("<img class='boatTile' src='assets/terrain/scope.png'/>")
                .addClass("busyShipTile");
                var letterTile = $("."+chosenShip+t).attr("data-gridLetter");
                var numberTile = $("."+chosenShip+t).attr("data-gridTile");
                switch(chosenShip){

                case "Patrol":
                    locationPatrol.push(letterTile + numberTile);
                    totalLocations.push(letterTile + numberTile);
                break;

                case "Destroyer":
                    locationDestroyer.push(letterTile + numberTile);
                    totalLocations.push(letterTile + numberTile);
                break;

                case "Submarine":
                    locationSubmarine.push(letterTile + numberTile);
                    totalLocations.push(letterTile + numberTile);
                break;

                case "Battleship":
                    locationBattleShip.push(letterTile + numberTile);
                    totalLocations.push(letterTile + numberTile);
                break;

                case "Carrier":
                    locationCarrier.push(letterTile + numberTile);
                    totalLocations.push(letterTile + numberTile);
                break;


                }

              }
              $("#tr"+chosenShip).remove();
              if(shipNumber < 5){

                shipNumber++;
                chosenShip = shipsList[shipNumber].boat;
                chosenShipLength = shipsList[shipNumber].shipLength;
                console.log(chosenShip);
              }

              }


          });



      }
      showData(gameData);
      showJson(gameData);
    })

    .fail(function() {
      alert( "error" );
    })
    /*.always(function() {
      alert( "complete" );
    });*/

});
var totalLocations = [];
var withBoats;
var repeat = false;
var times= 0;
var isHorizonatl = true;
var chosenShip = "Patrol";
var chosenShipLength = 2;
var alphabet = ["A","B","C","D","E","F","G","H","I","J"];
var locationPatrol = [];
var locationDestroyer = [];
var locationSubmarine = [];
var locationBattleShip = [];
var locationCarrier = [];
var shipNumber = 0;
var shipsList = [{boat:"Patrol", shipLength: 2},{boat:"Destroyer", shipLength: 3},{boat:"Submarine", shipLength: 3},{boat:"Battleship", shipLength: 4},{boat:"Carrier", shipLength: 5}];
//EVENT LISTENERS

$("#returnToGames").click(function(){
    location.assign("http://localhost:8080/games.html");

});

$("#horizontal").click(function(){
chekDirection();
});

$("#vertical").click(function(){
chekDirection();
});

$("#addShipLocations").click(function(){
checkBoatsLength();
location.reload();
});

function hoverEmptyBoats(){


        $(".tile").hover(function(){

        if(shipNumber < 5){
            if( $(this).attr("data-gridTile") > 0 && $(this).attr("data-gridTile") < 11 && $(this).children().length == 0 && $(this).hasClass("busyShipTile")==false ){
                  var actualNumber = parseInt($(this).attr("data-gridTile"));
                  var actualLetter = $(this).attr("data-gridLetter");
                  var hasBoatHorizontal;
                  var hasBoatVertical;
                  var count =0;
                  var count2 = 0;
                  var outsideTileHorizontal;
                  var outsideTileVertical;
                  var count3 = 0;
                  var count4 = 0;

                    for(var q = 0; q<chosenShipLength; q++){
                        if($("#alt2_"+ actualLetter + (actualNumber +q)).hasClass("busyShipTile") == false){
                            count++;
                    }
                    }
                    for(var l = 0; l<chosenShipLength; l++){
                        if($("#alt2_"+ alphabet[alphabet.indexOf(actualLetter)+p] + actualNumber ).hasClass("busyShipTile") == false){
                            count4++;
                    }
                    }
                    if(chosenShipLength == count4){
                    hasBoatVertical = false;
                    }
                    else{
                    hasBoatVertical = true;
                    }

                    if(chosenShipLength == count){
                    hasBoatHorizontal = false;
                    }
                    else{
                    hasBoatHorizontal = true;
                    }


                    if(isHorizonatl == true && hasBoatHorizontal == false) {
                     for (var y = 0; y<chosenShipLength; y++){
                                            if($("#alt2_"+ actualLetter + (actualNumber +y)).attr("data-gridTile") < 11 && $("#alt2_"+ actualLetter + (actualNumber +y)).attr("data-gridTile") > 0){
                                                count2++;
                                                }
                                        }
                     if(chosenShipLength == count2){
                                             outsideTileHorizontal = false;
                                         }
                                         else{
                                             outsideTileHorizontal = true;
                                         }
                     if(outsideTileHorizontal == false){
                         for(var i = 0; i<chosenShipLength; i++){
                             $("#alt2_"+ actualLetter + (actualNumber +i)).css("opacity","0.7");
                             $("#alt2_"+ actualLetter + (actualNumber +i)).addClass(chosenShip+i);
                               }}


 }
                    if(isHorizonatl == false && hasBoatVertical == false){

                    for (var p = 0; p<chosenShipLength; p++){
                        if(alphabet.indexOf($("#alt2_"+ alphabet[alphabet.indexOf(actualLetter)+p] + actualNumber ).attr("data-gridLetter")) > -1)
                        {
                            count3++;
                            }
                    }
                    if(chosenShipLength == count3){
                        outsideTileVertical = false;
                    }else{
                        outsideTileVertical = true;
                    }
                    if(outsideTileVertical == false){
                        for(var i = 0; i<chosenShipLength; i++){
                            $("#alt2_"+ alphabet[alphabet.indexOf(actualLetter)+i] + actualNumber ).css("opacity","0.7");
                            $("#alt2_"+ alphabet[alphabet.indexOf(actualLetter)+i] + actualNumber ).addClass(chosenShip+i);
                        }

                        }

                    }




                     }}

                     },
            function(){
                if( $(this).attr("data-gridTile") > 0 && $(this).attr("data-gridTile") < 11 && $(this).children().length == 0){
                    var actualNumber = parseInt($(this).attr("data-gridTile"));
                    var actualLetter = $(this).attr("data-gridLetter");

                       if(isHorizonatl){
                           for(var i = 0; i<chosenShipLength; i++){
                              $("#alt2_"+ actualLetter + (actualNumber+i)).css("opacity","1");
                              $("#alt2_"+ actualLetter + (actualNumber+i)).removeClass(chosenShip+i);
                                    }
                                  }
                        if(!isHorizonatl){
                           for(var i = 0; i<chosenShipLength; i++){
                           $("#alt2_"+ alphabet[alphabet.indexOf(actualLetter)+i] + actualNumber ).css("opacity","1");
                           $("#alt2_"+ alphabet[alphabet.indexOf(actualLetter)+i] + actualNumber ).removeClass(chosenShip+i);
                                                                  }
                                                              }
                                        }
                                     }

    )};

function chekDirection(){
    if($("#horizontal").is(':checked')){
        isHorizonatl = true;
    }else{
        isHorizonatl = false; }
};


function getParameterUrl(){
    var url = window.location.search;

    return url.substring(4);
};

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


function fillTheEmptyTiles(){
    for(var w = 0; w < chosenShipLength; w++){
        $("#alt2_"+ alphabet[alphabet.indexOf(actualLetter)+w] + actualNumber )
    }

}

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

};
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
};
function addShips(){

var ships = [{ boatType:"Submarine",locationList: locationSubmarine },
                                        {boatType:"Patrol",locationList:locationPatrol },
                                         {boatType:"Carrier",locationList: locationCarrier },
                                        {boatType:"BattleShip",locationList: locationBattleShip },
                                         {boatType:"Destroyer",locationList: locationDestroyer},
                                        ];

    $.post({
      url: "/api/ships/" + getParameterUrl() ,
      data: JSON.stringify(ships),
      dataType: "text",
      contentType: "application/json"
    })

    .done(function (response, status, jqXHR) {
      alert( "ship added: " + response );
    })
    .fail(function (jqXHR, status, httpError) {
        console.log(jqXHR);

    })

//location.reload();

};

function checkBoats(data){
    if(data.Ships.length == 0){
        withBoats=false;
    }
    else{
        withBoats=true;
    }
}

function checkBoatsLength(){
    var boatsCordenatesLenght = locationCarrier.length + locationBattleShip.length + locationSubmarine.length + locationDestroyer.length + locationPatrol.length;

    totalLocations.sort();
    var repeatedTiles = [];
    var countTiles = 0;
    var currentValue;
    var error = false;
    for (var i= 0; i<totalLocations.length; i++){
        currentValue = totalLocations[i];
        if(currentValue == totalLocations[i+1]){
         countTiles++;
        }

    }

    if(boatsCordenatesLenght != 17){
        alert("The boats are bad located, please refresh the page and try it again");
        error = true;
    }
    if(countTiles >0){
        alert("The boats are bad located, please refresh the page and try it again");
        error = true;
    }
    if(error == false){
        addShips();
    }

}

