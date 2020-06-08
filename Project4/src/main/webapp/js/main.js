// using jQuery and Ajax for form autocomplete
var taxRate = 0;
var shipping = 9.50;
if (document.getElementById('total-price') !== null) {
    var total = parseFloat(document.getElementById('total-price').innerHTML.substring(1));
}
$(document).ready(function() {
    $('#state').keyup(function() {
        var state = $(this).val();
        if (state !== '') {
            $.ajax({
                url: "./state",
                method: "GET",
                data: {
                    state: state
                },
                success: function(data) {
                    $('#stateList').html(data);
                }
            });
        }
    });
    $(document).on('click', 'li', function() {
        $('#state').val($(this).text());
        $('#stateList').fadeOut();
    });


    $('#zip').keyup(function() {
        var zip = $(this).val();
        if (zip.length > 2) {
            $.ajax({
                url: "./tax",
                method: "GET",
                data: {
                    zip: zip
                },
                success: function(data) {
                    $('#tax-rate').html(data);
                    taxRate = parseFloat(data).toFixed(3);
                    if(isNaN(taxRate))
                        taxRate = 0.09;
                    displayPrice();
                }
            });
        }
    });


    $('#method').change(function() {
        var choice = $(this).val();
        if (choice === "Overnight ($11.00)") {
            shipping = 11.00;
        }
        if (choice === "2-day expedited ($9.50)") {
            shipping = 9.50;
        }
        if (choice === "7-day ground ($6.25)") {
            shipping = 6.25;
        }
        displayPrice();
    });

    function displayPrice() {
        $('#total-price').html(total);
        $('#tax-amount').html((total * taxRate).toFixed(2));
        var final = (total + (total * taxRate) + shipping);
        $('#shipping').html(shipping.toFixed(2));
        $('#final-price').html(final.toFixed(2));
        $('input[id=totalPrice]').val(final.toFixed(2));
    };
    displayPrice();

    // billing address
    $('#billing-address').hide();

    $('#same-address').change(function() {
        $('#billing-address').slideToggle();
    })
});