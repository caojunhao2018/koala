<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en-ZH">
    <head>
        <style>
            .modal-body {
                display: block;
                text-align: center;
            }

            .left, .middle, .right{
                display:inline-block;
                font-size:14px;
                vertical-align:middle;
            }

            .left{
                width:350px;
            }

            .middle{
                width:75px;
                text-align:center;
            }

            .right{
                width:350px;
            }

        </style>
    </head>
    <body>
        <div class="modal fade">
            <div class="modal-dialog" style="width:800px;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title center" >授权角色</h4>
                    </div>
                    <div class="modal-body">
                        <div class="left">
                            <div class="left-modal-body">已授权角色</div>
                            <div id="grantAuthoritiesToUserGrid"></div>
                        </div>
                        <div class="middle ">
                            <button class="btn btn-success glyphicon glyphicon-chevron-left" id="grantAuthorityToUserButton">&nbsp;授权</button><br/><br/><br/><br/>
                            <button class="btn btn-danger glyphicon glyphicon-chevron-right" id="notGrantAuthorityToUserButton">&nbsp;撤权</button>
                            <br/><br/><br/><br/>
                            <div id="grantAuthorityToUserMessage"></div>
                        </div>
                        <div class="right">
                            <div class="right-modal-body">未授权角色</div>
                            <div id="notGrantAuthoritiesToUserGrid"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>