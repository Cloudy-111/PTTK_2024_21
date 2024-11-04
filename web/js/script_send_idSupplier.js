$(document).ready(function(){
    $(document).on('click', '.choose_supplier', function(event){
        event.preventDefault(); 
        var idSupplier = $(this).data('id'); 
        console.log(idSupplier);
        sendIdSupplier(idSupplier);
    });
});

function sendIdSupplier(idSupplier) {
    $.ajax({
        url: 'create_purchasebill', 
        data: { idSupplier: idSupplier },
        type: 'POST',
        success: function() {
            window.location.href = '/PTTK_2024_21/list_ingredient';
        },
        error: function() {
            alert("An error occurred while selecting the supplier.");
        }
    });
}