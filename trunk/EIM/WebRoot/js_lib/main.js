$(function(){
    $('#cc').layout();
    
	
    $('#menutree').tree([{
        text: 'Folder1',
        iconCls: 'icon-save',
        children: [{
            text: 'File1'
        }]
    }]);
    
});