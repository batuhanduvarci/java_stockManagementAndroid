// Generated code from Butter Knife. Do not modify!
package com.example.agent48.termproject.Menu;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class UpdateProduct$$ViewBinder<T extends com.example.agent48.termproject.Menu.UpdateProduct> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493016, "field 'updateButton'");
    target.updateButton = finder.castView(view, 2131493016, "field 'updateButton'");
    view = finder.findRequiredView(source, 2131493010, "field 'productCode'");
    target.productCode = finder.castView(view, 2131493010, "field 'productCode'");
    view = finder.findRequiredView(source, 2131493011, "field 'productName'");
    target.productName = finder.castView(view, 2131493011, "field 'productName'");
    view = finder.findRequiredView(source, 2131493012, "field 'productionDate'");
    target.productionDate = finder.castView(view, 2131493012, "field 'productionDate'");
    view = finder.findRequiredView(source, 2131493013, "field 'consumeDate'");
    target.consumeDate = finder.castView(view, 2131493013, "field 'consumeDate'");
    view = finder.findRequiredView(source, 2131493014, "field 'quantity'");
    target.quantity = finder.castView(view, 2131493014, "field 'quantity'");
    view = finder.findRequiredView(source, 2131493015, "field 'price'");
    target.price = finder.castView(view, 2131493015, "field 'price'");
  }

  @Override public void unbind(T target) {
    target.updateButton = null;
    target.productCode = null;
    target.productName = null;
    target.productionDate = null;
    target.consumeDate = null;
    target.quantity = null;
    target.price = null;
  }
}
