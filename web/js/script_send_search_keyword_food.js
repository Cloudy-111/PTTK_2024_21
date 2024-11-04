$(document).ready(function(){
    $('.form_search').on('submit', function(event) {
        var id = $(this).attr("id");
        event.preventDefault(); 
        searchFood(id); 
    });
})

function searchFood(id){
    var keyword = $('#keyword_search').val();
    
    $.ajax({
        url: id,
        data: {keyword: keyword},
        type: 'get',
        success: function(response){
            var newTable = $(response).find("tbody").html(); // Tìm phần tbody trong response
            $("tbody").html(newTable);
            var list = $('.list');
            if(id === "search_supplier"){
                if ($("tbody tr").length === 1 && $("tbody tr td").attr("colspan") === "5") {
                    if ($('#add_supplier').length === 0) {
                        list.append('<button class="add_btn" id="add_supplier">Add new Supplier</button>');
                    }
                } else {
                    $('#add_supplier').remove();
                }
            } else if(id === "search_ingredient"){
                if ($("tbody tr").length === 1 && $("tbody tr td").attr("colspan") === "5") {
                    if ($('#add_ingredient').length === 0) {
                        list.append(`
                            <div class="add-ingredient-form">
                                <input type="number" id="ingredient_price" placeholder="Enter ingredient price">
                                <button class="add_btn" id="add_ingredient">Add new Ingredient</button>
                            </div>
                        `);
                    }
                } else {
                    $('#add_ingredient').remove();
                }
            }
            
            
        },
        error: function(){
            alert("An error occurred while searching.");
        }
    })
}