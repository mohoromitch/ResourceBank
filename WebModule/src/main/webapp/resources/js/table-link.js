/**
 * Created by mitchellmohorovich on 15-08-28.
 */
//table link javascript, allows tables to be clickable.
jQuery(document).ready(function($) {
    $(".clickable-row-external").click(function() {
        window.open($(this).data("href"));
    });
    $(".clickable-row").click(function() {
        window.document.location = $(this).data("href");
    });
});
