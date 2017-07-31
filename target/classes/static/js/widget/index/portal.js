var minwidth = 282;
resizeWidth();
$(top.window).resize(function(e) {
    resizeWidth();
});
$(function() {
    $( ".content-list" ).sortable({
        revert: true,
        handle:'h2'
    });
});
function resizeWidth (){
    if($('#main').width() / 3 < minwidth){
        $('.content-item').width(($('#main').width() / 2) - 15);
    }else{
        $('.content-item').width(($('#main').width() / 3) - 15);
    }
}