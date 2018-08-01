package com.example.my_pinlocker;

import android.graphics.drawable.Drawable;

/**
 * The customization options for the buttons in {@link PinLockView}
 * passed to the {@link PinLockAdapter} to decorate the individual views
 *
 * Created by aritraroy on 01/06/16.
 */
public class CustomizationOptionsBundle {

    private int textColor;
    private int textSize;
    private int buttonSize;
    private Drawable buttonBackgroundDrawable;
    private Drawable deleteButtonDrawable;
    private Drawable submitButtonDrawable;
    private int deleteButtonSize;
    private int submitButtonSize;
    private boolean showDeleteButton;
    private boolean showSubmitButton;
    private int deleteButtonPressesColor;
    private int submitButtonPressesColor;

    public CustomizationOptionsBundle() {
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getButtonSize() {
        return buttonSize;
    }

    public void setButtonSize(int buttonSize) {
        this.buttonSize = buttonSize;
    }

    public Drawable getButtonBackgroundDrawable() {
        return buttonBackgroundDrawable;
    }

    public void setButtonBackgroundDrawable(Drawable buttonBackgroundDrawable) {
        this.buttonBackgroundDrawable = buttonBackgroundDrawable;
    }

    public Drawable getDeleteButtonDrawable() {
        return deleteButtonDrawable;
    }

    public Drawable getSubmitButtonDrawable() {
        return submitButtonDrawable;
    }

    public void setDeleteButtonDrawable(Drawable deleteButtonDrawable) {
        this.deleteButtonDrawable = deleteButtonDrawable;
    }

    public void setSubmitButtonDrawable(Drawable submitButtonDrawable) {
        this.submitButtonDrawable = submitButtonDrawable;
    }

    public int getDeleteButtonSize() {
        return deleteButtonSize;
    }

    public int getSubmitButtonSize() {
        return submitButtonSize;
    }

    public void setDeleteButtonSize(int deleteButtonSize) {
        this.deleteButtonSize = deleteButtonSize;
    }
    public void setSubmitButtonSize(int submitButtonSize) {
        this.submitButtonSize = submitButtonSize;
    }

    public boolean isShowDeleteButton() {
        return showDeleteButton;
    }

    public boolean isShowSubmitButton() {
        return showSubmitButton;
    }

    public void setShowDeleteButton(boolean showDeleteButton) {
        this.showDeleteButton = showDeleteButton;
    }

    public void setShowSubmitButton(boolean showSubmitButton) {
        this.showSubmitButton = showSubmitButton;
    }

    public void setDeleteButtonPressesColor(int deleteButtonPressesColor) {
        this.deleteButtonPressesColor = deleteButtonPressesColor;
    }

    public void setSubmitButtonPressesColor(int submitButtonPressesColor) {
        this.submitButtonPressesColor = submitButtonPressesColor;
    }
}
