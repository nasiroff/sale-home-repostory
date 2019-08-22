function addToFavoritePost (id) {

    $.ajax({
        url: '/user/addToFavorite/'+id,
        type: 'GET',
        success: function () {
            alert("Post added to favorite posts");
            $("#"+id).removeClass("fa-star-o");
            $("#"+id).addClass("fa-star");
            $("#"+id).removeAttr("onclick");
            $("#"+id).attr("onclick", "deleteFromFavoritePost("+id+")")
        }

    });


}

function deleteFromFavoritePost(id) {

    $.ajax({
        url: '/user/deleteFromFavoritePost/'+id,
        type: 'GET',
        success: function () {
            alert("Post deleted from favorite posts");
            $("#"+id).removeClass("fa-star");
            $("#"+id).addClass("fa-star-o");
            $("#"+id).removeAttr("onclick");
            $("#"+id).attr("onclick", "addToFavoritePost("+id+")")
        }

    });
}