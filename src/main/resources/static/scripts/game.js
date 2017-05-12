$(document).ready(function(){

  $.ajax({
    type: 'GET',
    url: "api/game_view/" + getParameterUrl()

   }).done(function(data) {
      alert( "success" );
      createGridWithLava();
      var gameData = data;
      putVotes(gameData);
      console.log(gameData);

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

