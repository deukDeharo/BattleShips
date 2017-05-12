$(document).ready(function(){

  $.ajax({
    type: 'GET',
    url: "api/games"

   }).done(function(data) {
      alert( "success" );
      createList(data);
    })
    .fail(function() {
      alert( "error" );
    })
    .always(function() {
      alert( "complete" );
    });

});

function createList(data){
    var gameList = data.Games;
    console.log(gameList);


    for(var i  = 0; i<gameList.length; i++){
        $(document.createElement("li")).appendTo("#Games").text("Game "+i).attr("id", "Game"+i);
        $(document.createElement("ul")).appendTo("#Game"+i).attr("id","ul_1"+i);
        $(document.createElement("li")).appendTo("#ul_1"+i).attr("id","date"+i).text("date: " + gameList[i].date);
        $(document.createElement("li")).appendTo("#ul_1"+i).attr("id","id_1"+i).text("id: " + gameList[i].id);
        $(document.createElement("li")).appendTo("#ul_1"+i).attr("id","participation"+i).text("participations");
        $(document.createElement("ul")).appendTo("#participation"+i).attr("id","listParticipation"+i);


        for(var j = 0; j<gameList[i].participation.length ;j++){
            $(document.createElement("li")).appendTo("#listParticipation"+i).text("id"+gameList[i].participation[j].id);
            $(document.createElement("li")).appendTo("#listParticipation"+i).attr("id", "player"+j+"inGame"+i).text("player" +j);


            $(document.createElement("ul")).appendTo("#player"+j+"inGame"+i).attr("id", "pLayerList"+j+"inGame"+i);


             $(document.createElement("li")).appendTo("#pLayerList"+j+"inGame"+i).text("id :"+gameList[i].participation[j].player.id);
             $(document.createElement("li")).appendTo("#pLayerList"+j+"inGame"+i).text("userName :"+gameList[i].participation[j].player.userName);


        }
    }
};





