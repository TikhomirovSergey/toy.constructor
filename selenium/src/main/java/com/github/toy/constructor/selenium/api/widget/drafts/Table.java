package com.github.toy.constructor.selenium.api.widget.drafts;

import com.github.toy.constructor.selenium.api.widget.HasValue;
import com.github.toy.constructor.selenium.api.widget.Name;
import com.github.toy.constructor.selenium.api.widget.Widget;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

@Name("Table")
public abstract class Table extends Widget implements HasValue<Map<String, List<String>>> {

    public Table(WebElement wrappedElement) {
        super(wrappedElement);
    }

}
