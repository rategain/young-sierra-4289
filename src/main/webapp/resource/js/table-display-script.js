$(document).ready(function() {

    $('.jqueryDataTable').dataTable( {
        'bProcessing': false,
        'bServerSide': false,
        'sAjaxSource': './LogDataServlet',
        'bJQueryUI': true,
        'aoColumns': [
            { 'mData': 'IP' },
            { 'mData': 'Details' }
            
        ]
    } ); 
} );


