/**
 * PostbirdAlertBox.js
 * -    原生javascript弹框插件
 * Author:  Postbird - http://www.ptbird.cn
 * License: MIT
 * Date:    2017-09-23
 */
var PostbirdAlertBox = {
    containerClass: "postbird-box-container active",
    textTemplate: {
        title: "提示",
        content: "提示内容",
        okBtn: "确认",
        cancelBtn: "取消",
        contentColor: "3C3C3C",
        okBtnColor: "#0e90d2",
        promptTitle: "提示",
        promptOkBtn: "确认",
    },
    getAlertTemplate: function () {
        var temp = '<div class="postbird-box-dialog">'
            + '<div class="postbird-box-content">'
            + '<div class="postbird-box-header">'
            + '<span class="postbird-box-close-btn">×</span>'
            + '<span class="postbird-box-title">' + "<span >"
            + this.textTemplate.title + "</span>" + "</span>" + "</div>"
            + '<div class="postbird-box-text">' + '<span style="color:' + this.textTemplate.contentColor + ';">'
            + this.textTemplate.content + "</span>" + "</div>" + '<div class="postbird-box-footer">'
            + '<button class="btn-footer btn-block-footer btn-footer-ok" style="color:' + this.textTemplate.okBtnColor + ';">'
            + this.textTemplate.okBtn + "</button>" + "</div>" + "</div>" + "</div>";
        return temp
    },
    getConfirmTemplate: function () {
        return '<div class="postbird-box-container">'
            + '<div class="postbird-box-dialog">'
            + '<div class="postbird-box-content">'
            + '<div class="postbird-box-header">'
            + '<span class="postbird-box-close-btn">×</span>'
            + '<span class="postbird-box-title">' + "<span >"
            + this.textTemplate.title + "</span>" + "</span>" + "</div>" + '<div class="postbird-box-text">'
            + '<span style="color:' + this.textTemplate.contentColor + ';">' + this.textTemplate.content + "?</span>" + "</div>"
            + '<div class="postbird-box-footer">'
            + '<button class="btn-footer btn-left-footer btn-footer-cancel" style="color:' + this.textTemplate.cancelBtnColor + ';">'
            + this.textTemplate.cancelBtn + "</button>"
            + '<button class="btn-footer btn-right-footer btn-footer-ok"  style="color:' + this.textTemplate.okBtnColor + ';">'
            + this.textTemplate.okBtn + "</button>" + "</div>" + "</div>" + "</div>" + "</div>"
    },
    getPromptTemplate: function () {
        return '<div class="postbird-box-container">' + '<div class="postbird-box-dialog">' + '<div class="postbird-box-content">' + '<div class="postbird-box-header">' + '<span class="postbird-box-close-btn">×</span>' + '<span class="postbird-box-title">' + "<span >" + this.textTemplate.title + "</span>" + "</span>" + "</div>" + '<div class="postbird-box-text">' + '<input type="text" class="postbird-prompt-input" autofocus="true" >' + "</div>" + '<div class="postbird-box-footer">' + '<button class="btn-footer btn-left-footer btn-footer-cancel" style="color:' + this.textTemplate.cancelBtnColor + ';">' + this.textTemplate.cancelBtn + "</button>" + '<button class="btn-footer btn-right-footer btn-footer-ok"  style="color:' + this.textTemplate.okBtnColor + ';">' + this.textTemplate.okBtn + "</button>" + "</div>" + "</div>" + "</div>" + "</div>"
    },
    alert: function (opt) {
        this.textTemplate.title = opt.title || this.textTemplate.title;
        this.textTemplate.content = opt.content || this.textTemplate.content;
        this.textTemplate.okBtn = opt.okBtn || this.textTemplate.okBtn;
        this.textTemplate.okBtnColor = opt.okBtnColor || this.textTemplate.okBtnColor;
        this.textTemplate.contentColor = opt.contentColor || this.textTemplate.contentColor;
        var box = document.createElement("div"), _this = this;
        box.className = this.containerClass;
        box.innerHTML = this.getAlertTemplate();
        document.body.appendChild(box);
        var btn = document.getElementsByClassName("btn-footer-ok");
        btn[btn.length - 1].onclick = function () {
            if (opt.onConfirm) {
                opt.onConfirm()
            }
            _this.removeBox(box)
        };
        $("body").on("touchmove",function(event){
            event.preventDefault;
        }, false);
    },
    confirm: function (opt) {
        this.textTemplate.title = opt.title || this.textTemplate.promptTitle;
        this.textTemplate.promptPlaceholder = opt.promptPlaceholder || this.textTemplate.promptPlaceholder;
        this.textTemplate.okBtn = opt.okBtn || this.textTemplate.promptOkBtn;
        this.textTemplate.okBtnColor = opt.okBtnColor || this.textTemplate.okBtnColor;
        this.textTemplate.cancelBtn = opt.cancelBtn || this.textTemplate.cancelBtn;
        this.textTemplate.cancelBtnColor = opt.cancelBtnColor || this.textTemplate.cancelBtnColor;
        this.textTemplate.content = opt.content || this.textTemplate.content;
        var box = document.createElement("div"), _this = this;
        box.className = this.containerClass;
        box.innerHTML = this.getConfirmTemplate();
        document.body.appendChild(box);
        var okBtn = document.getElementsByClassName("btn-footer-ok");
        okBtn[okBtn.length - 1].onclick = function () {
            if (opt.onConfirm) {
                opt.onConfirm()
            }
            _this.removeBox(box)
        };
        var cancelBtn = document.getElementsByClassName("btn-footer-cancel");
        cancelBtn[cancelBtn.length - 1].onclick = function () {
            if (opt.onCancel) {
                opt.onCancel()
            }
            _this.removeBox(box)
        };
        $("body").on("touchmove",function(event){
            event.preventDefault;
        }, false)
    },
    prompt: function (opt) {
        this.textTemplate.title = opt.title || this.textTemplate.title;
        this.textTemplate.content = opt.content || this.textTemplate.content;
        this.textTemplate.contentColor = opt.contentColor || this.textTemplate.contentColor;
        this.textTemplate.okBtn = opt.okBtn || this.textTemplate.okBtn;
        this.textTemplate.okBtnColor = opt.okBtnColor || this.textTemplate.okBtnColor;
        this.textTemplate.cancelBtn = opt.cancelBtn || this.textTemplate.cancelBtn;
        this.textTemplate.cancelBtnColor = opt.cancelBtnColor || this.textTemplate.cancelBtnColor;
        var box = document.createElement("div"), _this = this;
        box.className = this.containerClass;
        box.innerHTML = this.getPromptTemplate();
        document.body.appendChild(box);
        var promptInput = document.getElementsByClassName("postbird-prompt-input");
        promptInput = promptInput[promptInput.length - 1];
        promptInput.focus();
        var okBtn = document.getElementsByClassName("btn-footer-ok");
        var inputData = promptInput.value;
        okBtn[okBtn.length - 1].onclick = function () {
            if (opt.onConfirm) {
                opt.onConfirm(promptInput.value)
            }
            _this.removeBox(box)
        };
        var cancelBtn = document.getElementsByClassName("btn-footer-cancel");
        cancelBtn[cancelBtn.length - 1].onclick = function () {
            if (opt.onCancel) {
                opt.onCancel(promptInput.value)
            }
            _this.removeBox(box)
        }
    },
    removeBox: function (box) {
        var box = document.getElementsByClassName(this.containerClass);
        document.body.removeChild(box[box.length - 1])
        $("body").off("touchmove");
    }
};