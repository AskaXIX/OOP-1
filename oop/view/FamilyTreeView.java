package oop.view;

import oop.presenter.FamilyTreePresenter;

public interface FamilyTreeView {
    void showPersons(String persons);
    void showMessage(String message);
    void showError(String error);
    void setPresenter(FamilyTreePresenter presenter);
}