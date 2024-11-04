$(document).ready(function() {
    $(document).on('click', '.choose_ingredient', function(event) {
        event.preventDefault();
        
        var ingredientId = $(this).data('id');
        $('#ingredient_id').val(ingredientId);
        $('#quantity_form').show();
    });

    $('#ingredient_quantity_form').on('submit', function(event) {
        event.preventDefault();
        
        var idIngredient = $('#ingredient_id').val();
        var idPurchaseBill = $('#purchase_bill_id').val();
        var amountIngredient = $('#quantity').val();
        
        $.ajax({
            url: 'create_new_purchaseBill_ingredient', 
            type: 'POST',
            dataType: 'json',
            data: {
                idIngredient: idIngredient,
                idPurchaseBill: idPurchaseBill,
                amountIngredient: amountIngredient
            },
            success: function(response) {
                if (response.status === "success") {
                    alert("Ingredient added successfully!");
                    $('#quantity').val('');
                    $('#quantity_form').hide();
                } else {
                    alert("Failed to add ingredient: " + response.message);
                }
            },
            error: function() {
                alert("An error occurred while adding the ingredient.");
            }
        });
    });

    $('#cancel').on('click', function() {
        $('#quantity_form').hide();
    });
});
