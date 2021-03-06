package com.example.my_pinlocker;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by aritraroy on 31/05/16.
 */
public class PinLockAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public int min_pin_length;
    public int max_pin_length;

    private static final int VIEW_TYPE_NUMBER = 0;
    private static final int VIEW_TYPE_DELETE = 1;
    private static final int VIEW_TYPE_SUBMIT = 2;

    private Context mContext;
    private CustomizationOptionsBundle mCustomizationOptionsBundle;
    private OnNumberClickListener mOnNumberClickListener;
    private OnDeleteClickListener mOnDeleteClickListener;
    private OnSubmitClickListener mOnSubmitClickListener;
    private int mPinLength;

    private int[] mKeyValues;

    public PinLockAdapter(Context context) {
        this.min_pin_length = 4;
        this.mContext = context;
        this.mKeyValues = getAdjustKeyValues(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0});
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_NUMBER) {
            View view = inflater.inflate(R.layout.layout_number_item, parent, false);
            viewHolder = new NumberViewHolder(view);
        } else if (viewType == VIEW_TYPE_DELETE) {
            View view = inflater.inflate(R.layout.layout_delete_item, parent, false);
            viewHolder = new DeleteViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.layout_submit_item, parent, false);
            viewHolder = new SubmitViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_NUMBER) {
            NumberViewHolder vh1 = (NumberViewHolder) holder;
            configureNumberButtonHolder(vh1, position);
        } else if (holder.getItemViewType() == VIEW_TYPE_DELETE) {
            DeleteViewHolder vh2 = (DeleteViewHolder) holder;
            configureDeleteButtonHolder(vh2);
        } else if (holder.getItemViewType() == VIEW_TYPE_SUBMIT) {
            SubmitViewHolder vh3 = (SubmitViewHolder) holder;
            configureSubmitButtonHolder(vh3);
        }
    }

    private void configureNumberButtonHolder(NumberViewHolder holder, int position) {
        if (holder != null) {
            if (position == 9) {
                holder.mNumberButton.setVisibility(View.GONE);
            } else {
                holder.mNumberButton.setText(String.valueOf(mKeyValues[position]));
                holder.mNumberButton.setVisibility(View.VISIBLE);
                holder.mNumberButton.setTag(mKeyValues[position]);
            }

            if (mCustomizationOptionsBundle != null) {
                holder.mNumberButton.setTextColor(mCustomizationOptionsBundle.getTextColor());
                if (mCustomizationOptionsBundle.getButtonBackgroundDrawable() != null) {
                    if (Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        holder.mNumberButton.setBackgroundDrawable(
                                mCustomizationOptionsBundle.getButtonBackgroundDrawable());
                    } else {
                        holder.mNumberButton.setBackground(
                                mCustomizationOptionsBundle.getButtonBackgroundDrawable());
                    }
                }
                holder.mNumberButton.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        mCustomizationOptionsBundle.getTextSize());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        mCustomizationOptionsBundle.getButtonSize(),
                        mCustomizationOptionsBundle.getButtonSize());
                holder.mNumberButton.setLayoutParams(params);
            }
        }
    }

    private void configureDeleteButtonHolder(DeleteViewHolder holder) {
        if (holder != null) {
            if (mCustomizationOptionsBundle.isShowDeleteButton() && mPinLength > 0) {
                holder.mButtonImage.setVisibility(View.VISIBLE);
                if (mCustomizationOptionsBundle.getDeleteButtonDrawable() != null) {
                    holder.mButtonImage.setImageDrawable(mCustomizationOptionsBundle.getDeleteButtonDrawable());
                }
                holder.mButtonImage.setColorFilter(mCustomizationOptionsBundle.getTextColor(),
                        PorterDuff.Mode.SRC_ATOP);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        mCustomizationOptionsBundle.getDeleteButtonSize(),
                        mCustomizationOptionsBundle.getDeleteButtonSize());
                holder.mButtonImage.setLayoutParams(params);
            } else {
                holder.mButtonImage.setVisibility(View.GONE);
            }
        }
    }

    private void configureSubmitButtonHolder(SubmitViewHolder holder) {
        if (holder != null) {
            if (mCustomizationOptionsBundle.isShowSubmitButton() && mPinLength > this.min_pin_length - 1) {
                holder.mSubmitButtonImage.setVisibility(View.VISIBLE);
                if (mCustomizationOptionsBundle.getSubmitButtonDrawable() != null) {
                    holder.mSubmitButtonImage.setImageDrawable(mCustomizationOptionsBundle.getSubmitButtonDrawable());
                }
                holder.mSubmitButtonImage.setColorFilter(mCustomizationOptionsBundle.getTextColor(),
                        PorterDuff.Mode.SRC_ATOP);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        mCustomizationOptionsBundle.getSubmitButtonSize(),
                        mCustomizationOptionsBundle.getSubmitButtonSize());
                holder.mSubmitButtonImage.setLayoutParams(params);
            } else {
                holder.mSubmitButtonImage.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return VIEW_TYPE_DELETE;
        }
        if (position == getItemCount() - 3) {
            return VIEW_TYPE_SUBMIT;
        }
        return VIEW_TYPE_NUMBER;
    }

    public int getPinLength() {
        return mPinLength;
    }

    public void setPinLength(int pinLength) {
        this.mPinLength = pinLength;
    }

    public int[] getKeyValues() {
        return mKeyValues;
    }

    public void setKeyValues(int[] keyValues) {
        this.mKeyValues = getAdjustKeyValues(keyValues);
        notifyDataSetChanged();
    }

    private int[] getAdjustKeyValues(int[] keyValues) {
        int[] adjustedKeyValues = new int[keyValues.length + 1];
        for (int i = 0; i < keyValues.length; i++) {
            if (i < 9) {
                adjustedKeyValues[i] = keyValues[i];
            } else {
                adjustedKeyValues[i] = -1;
                adjustedKeyValues[i + 1] = keyValues[i];
            }
        }
        return adjustedKeyValues;
    }


    public void setOnItemClickListener(OnNumberClickListener onNumberClickListener) {
        this.mOnNumberClickListener = onNumberClickListener;
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.mOnDeleteClickListener = onDeleteClickListener;
    }

    public void setOnSubmitClickListener(OnSubmitClickListener onSubmitClickListener) {
        this.mOnSubmitClickListener = onSubmitClickListener;
    }

    public void setCustomizationOptions(CustomizationOptionsBundle customizationOptionsBundle) {
        this.mCustomizationOptionsBundle = customizationOptionsBundle;
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder {
        Button mNumberButton;

        public NumberViewHolder(final View itemView) {
            super(itemView);
            mNumberButton = (Button) itemView.findViewById(R.id.button);
            mNumberButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnNumberClickListener != null) {
                        mOnNumberClickListener.onNumberClicked((Integer) v.getTag());
                    }
                }
            });
        }
    }

    public class DeleteViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mDeleteButton;
        ImageView mButtonImage;

        public DeleteViewHolder(final View itemView) {
            super(itemView);
            mDeleteButton = (LinearLayout) itemView.findViewById(R.id.button);
            mButtonImage = (ImageView) itemView.findViewById(R.id.button_delete);

            if (mCustomizationOptionsBundle.isShowDeleteButton() && mPinLength > 0) {
                mDeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnDeleteClickListener != null) {
                            mOnDeleteClickListener.onDeleteClicked();
                        }
                    }
                });

                mDeleteButton.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (mOnDeleteClickListener != null) {
                            mOnDeleteClickListener.onDeleteLongClicked();
                        }
                        return true;
                    }
                });

            }
        }
    }

    public class SubmitViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mSubmitButton;
        ImageView mSubmitButtonImage;

        public SubmitViewHolder(final View itemView) {
            super(itemView);
            mSubmitButton = (LinearLayout) itemView.findViewById(R.id.button);
            mSubmitButtonImage = (ImageView) itemView.findViewById(R.id.button_submit);

            if (mCustomizationOptionsBundle.isShowSubmitButton() && mPinLength > 0) {
                mSubmitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnSubmitClickListener != null) {
                            mOnSubmitClickListener.onSubmitClicked();
                        }
                    }
                });



            }
        }
    }

    public interface OnNumberClickListener {
        void onNumberClicked(int keyValue);
    }

    public interface OnDeleteClickListener {
        void onDeleteClicked();

        void onDeleteLongClicked();
    }

    public interface OnSubmitClickListener {
        void onSubmitClicked();

    }
}
