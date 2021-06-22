(function ($) {
    'use strict';
    if (typeof Dropzone !== 'undefined' && Dropzone !== null && typeof Dropzone === "function") {
        const $dz = $("#dz");
        const $formError = $(".contact-form-error");
        Dropzone.autoDiscover = false;
        $dz.dropzone({
            url: $dz.data('url'),
            acceptedFiles: 'image/*',
            autoProcessQueue: false,
            uploadMultiple: true,
            parallelUploads: 6,
            maxFiles: 6,
            addRemoveLinks: true,
            dictDefaultMessage: '選擇圖片',
            dictRemoveFile: '刪除檔案',
            dictMaxFilesExceeded: '最多上傳六張圖片',
            dictSetPrimary: '設定為封面',
            init: function() {
                let radioChangeEvent, radioElement, acceptedFiles;
                this.on('addedfile', file => {
                    let inputString;
                    if(this.options.dictSetPrimary) {
                        inputString = `<div class="form-check"><input class="form-check-input" type="radio" name="setPrimary" id="${file.upload.uuid}"><label class="form-check-label" for="${file.upload.uuid}">${this.options.dictSetPrimary}</label></div>`;
                        file.previewElement.appendChild(Dropzone.createElement(inputString));
                    }
                    acceptedFiles = this.getAcceptedFiles();
                    radioChangeEvent = (function(_this) {
                        return function() {
                            _this.files.forEach(file => file.isPrimary = false)
                            file.isPrimary = true;
                        }
                    })(this);
                    if(acceptedFiles.length < this.options.maxFiles) {
                        radioElement = file.previewElement.querySelector("input[type=radio][name=setPrimary]");
                        radioElement.addEventListener('change', radioChangeEvent);
                    }
                })
                this.on('error', (file, message) => {
                    if(message) {
                        $formError.removeClass('d-none');
                        $formError.text(message);
                    }
                    if(!$formError.hasClass('d-none')) {
                        window.setTimeout(function() {
                            $formError.addClass('d-none');
                        }, 5000);
                    }
                });
                this.on('maxfilesexceeded', file => this.removeFile(file));
                this.on('queuecomplete', file => console.log('queuecomplete'));
                $('#dzUpload').on('click', () => this.processQueue());
                $('#dzCancel').on('click', () => this.removeAllFiles(true))
            },
        })
    }
}).apply(this, [jQuery]);

(function ($) {
    'use strict';
    if($.fn.datetimepicker !== undefined) {
        const $dpStart = $('#dpStart');
        const $dpEnd = $('#dpEnd');
        const icons = {
            time: "fa fa-clock-o",
            date: "fa fa-calendar",
            up: "fa fa-arrow-up",
            down: "fa fa-arrow-down"
        }
        $dpStart.datetimepicker({
            format: 'YYYY-MM-DD HH:mm:ss',
            sideBySide: true,
            icons: icons
        });
        $dpEnd.datetimepicker({
            format: 'YYYY-MM-DD HH:mm:ss',
            sideBySide: true,
            icons: icons,
            useCurrent: false
        });
        $dpStart.on('dp.change', function(e) {
            $dpEnd.data('DateTimePicker').minDate(e.date);
        });
        $dpEnd.on('dp.change', function (e) {
            $dpStart.data('DateTimePicker').maxDate(e.date);
        });
    }
}).apply(this, [jQuery]);

