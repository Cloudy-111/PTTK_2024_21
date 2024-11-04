$(document).ready(function(){
    $(document).on('click', '#add_ingredient', function(event){
        event.preventDefault(); 
//        var idWarehouser = $('#memberInfo').data('id');
        var name_ingredient = $('#keyword_search').val();
        var price = $('#ingredient_price').val();
        createNewIngredient(name_ingredient, price);
    });
});

function createNewIngredient(name_ingredient, price) {
    $.ajax({
        url: 'create_new_ingredient', 
        data: { name_ingredient: name_ingredient, price: price },
        type: 'POST',
        dataType: 'json',
        success: function(response) {
            if (response.status === "success") {
//                window.location.href = "/PTTK_2024_21/list_ingredient";
                alert(response.message);
            } else {
                alert(response.message);
            }
        },
        error: function() {
            alert("An error occurred while create new ingredient.");
        }
    });
}