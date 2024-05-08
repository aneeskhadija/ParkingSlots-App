// Generated by view binder compiler. Do not edit!
package com.example.task.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.task.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySecondBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView first;

  @NonNull
  public final TextView fourth;

  @NonNull
  public final TextView second;

  @NonNull
  public final TextView third;

  private ActivitySecondBinding(@NonNull LinearLayout rootView, @NonNull TextView first,
      @NonNull TextView fourth, @NonNull TextView second, @NonNull TextView third) {
    this.rootView = rootView;
    this.first = first;
    this.fourth = fourth;
    this.second = second;
    this.third = third;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySecondBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySecondBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_second, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySecondBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.first;
      TextView first = ViewBindings.findChildViewById(rootView, id);
      if (first == null) {
        break missingId;
      }

      id = R.id.fourth;
      TextView fourth = ViewBindings.findChildViewById(rootView, id);
      if (fourth == null) {
        break missingId;
      }

      id = R.id.second;
      TextView second = ViewBindings.findChildViewById(rootView, id);
      if (second == null) {
        break missingId;
      }

      id = R.id.third;
      TextView third = ViewBindings.findChildViewById(rootView, id);
      if (third == null) {
        break missingId;
      }

      return new ActivitySecondBinding((LinearLayout) rootView, first, fourth, second, third);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
