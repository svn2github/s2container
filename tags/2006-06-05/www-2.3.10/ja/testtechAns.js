//  �����̔z��(�z��̏��Ԃ͓K����OK)
    ans = new Array("Mock2", "Mock2_4", "Mock3_3", "Use1", "Use2_2", "Use3_4", "Unit4", "Auto1",
                    "Methods3", "Include3", "Include2_4", "Value3", "Value2_2", "SetUp3", "SetUp2_1", "tearDown3",
                    "tearDown2_2", "tx1", "assert4", "dao3", "readXls1","readXls2_4", "reload4",
                    "reload2_4", "readXlsWriteDb2", "readXlsWriteDb2_4","readXlsWriteDb3_2",
                    "ExcelMake2","SqlReader1","XlsWriter4","DaoTestCase3","DaoTestCase2_2");
function check(linkName,linkNextName){
    count = 0;
    message = "";
    //x�ϐ��̏�����
    x = 0;
    for(i = 0; i<4; i++){
        if(document.myForm.elements["" + linkName][i].checked){
            if(answerCheck(document.myForm.elements["" + linkName][i].value)){
                message = "�����ł�!\n�����\�����܂����H";
                flag=confirm(message);
                if(flag){
                    location.href="testtechAns.html#" + linkName
                }
                else{
                    if(linkNextName != ""){
                        flag=confirm("���̖��Ɉڂ�܂����H");
                        if(flag){
                            location.href = "#" + linkNextName
                        }
                    }
                }
            }
            else{
                message = "�s�����ł��B\n�����\�����܂����H";
                flag=confirm(message);
                if(flag){
                    location.href="testtechAns.html#" + linkName
                }
            }
            return true;
        }
        count = count + 1;
    }
    
    if(count == 4){
        alert("�����ꂩ�Ƀ`�F�b�N�����Ă���u�𓚂�GO�v�{�^���������Ă�������");  
    }
}

function answerCheck(answer){
     
    for(i = 0 ; i<ans.length; i++){
        if(ans[i] == answer){
            return true;
        }
    }
    return false;
}