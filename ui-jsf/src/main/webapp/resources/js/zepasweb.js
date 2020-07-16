// Set ajax timeout [ms] to a bigger value (=620s) as transaction timeout (=600s)
$.ajaxSetup({timeout: 620000});

function ChangeManagerItem(_remoteCommandChanged, _dirty) {
  this.remoteCommandChanged = _remoteCommandChanged;
  this.dirty = _dirty;
  this.changed = function () {
    if (this.dirty === false) {
      if (this.remoteCommandChanged == null) {
        alert('Keine remoteCommandChanged-Funktion definiert.');
      } else {
        this.remoteCommandChanged();
        this.dirty = true;
      }
    }
  };
  this.isDirty = function () {
    return this.dirty;
  };
  this.setDirty = function (_dirty) {
    this.dirty = _dirty;
  };
}

function ChangeManagerClass() {
  this.registered = new Array();
  this.register = function (_name, _remoteCommandChanged, _valid) {
    var dirty = !_valid;
    var item = this.registered[_name];
    if (typeof (item) == 'undefined') {
      item = new ChangeManagerItem(_remoteCommandChanged, dirty);
      this.registered[_name] = item;
    } else {
      item.setDirty(dirty);
    }
  };
  this.getItem = function (_name) {
    var item = this.registered[_name];
    if (typeof (item) == 'undefined') {
      alert('Kein Item mit diesem Namen: ' + _name);
    }
    return item;
  };
  this.changed = function (_name) {
    this.getItem(_name).changed();
  };
  this.setDirty = function (_name, _dirty) {
    this.getItem(_name).setDirty(_dirty);
  };
  this.reset = function (_name) {
    this.getItem(_name).setDirty(false);
  };
}

ChangeManager = new ChangeManagerClass();

function SendMail(mailAddress, rowIndex) {
  // Check whether application runs on Helvetia notebook
  if (navigator.userAgent.indexOf('Windows NT 6.1; WOW64;') >= 0) {
    // Non standard Helvetia Notebook -> may be VM
    MailWindow = window.open('mailto:' + mailAddress, 'Send Mail ' + rowIndex);
    MailWindow.focus();
  } else {
    // Standard Helvetia Notebook
    MailWindow = window.open('mailto:' + mailAddress, 'Send Mail ' + rowIndex);
    // Close opened browser tab - Lotus Notes is used to send email.
    MailWindow.focus();
  }
}

function openDetailSection(rowIndex) {
  $(document).ready(function () {
    if (rowIndex >= 0) {
      $("tbody>tr[data-ri='" + rowIndex + "']>td>div.ui-row-toggler").trigger("click");
    }
  });
}

function autoSearch() {
  autoSuche = document.getElementById('autoZweitpersonenSucheId');
  if (autoSuche === null) {
    return;
  }
  button = document.getElementById('formPersonensucheId:tabId:buttonSearch1Id');
  if (button) {
    button.click();
  } else {
    button = document.getElementById('formPersonensucheId:tabId:buttonSearch3Id');
    if (button) {
      button.click();
    }
  }
}
