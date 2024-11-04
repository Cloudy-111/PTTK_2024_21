//$(document).ready(function(){
//    $(document).on('click', '#show_bill_btn', function(e){
//        e.preventDefault();
//        var idPurchaseBill = $(this).data('id');
//        
//        $.ajax({
//            url: 'purchasebill_confirm', 
//            type: 'GET',
//            dataType: 'json',
//            data: {
//                idPurchaseBill: idPurchaseBill
//            },
//            success: function() {
//                window.location.href = '/PTTK_2024_21/purchasebill_confirm';
//            },
//            error: function() {
//                alert("An error occurred while adding the ingredient.");
//            }
//        });
//    })
//})

$(document).ready(function() {
    $(document).on('click', '#show_bill_btn', function(e) {
        e.preventDefault();  
        $('#showBillForm').submit();  // Gửi form
    });
});