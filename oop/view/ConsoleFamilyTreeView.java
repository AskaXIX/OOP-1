package oop.view;

import java.util.Scanner;

import oop.model.FamilyTreeFileHandler;
import oop.presenter.FamilyTreePresenter;
import oop.presenter.FamilyTreePresenterImpl;

public class ConsoleFamilyTreeView implements FamilyTreeView {
    private FamilyTreePresenter presenter;
    private final Scanner scanner;

    public ConsoleFamilyTreeView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void setPresenter(FamilyTreePresenter presenter) {
        this.presenter = presenter;
    }

    public void start() {
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] tokens = input.split("\\s+");
            if (tokens.length == 0) continue;

            String command = tokens[0];
            switch (command) {
                case "add":
                    handleAddCommand(tokens);
                    break;
                case "list":
                    presenter.listPersons();
                    break;
                case "sort":
                    handleSortCommand(tokens);
                    break;
                case "save":
                    handleSaveCommand(tokens);
                    break;
                case "load":
                    handleLoadCommand(tokens);
                    break;
                case "exit":
                    return;
                default:
                    showError("Unknown command");
            }
        }
    }

    private void handleAddCommand(String[] tokens) {
        if (tokens.length < 3) {
            showError("Usage: add person <name> <birth_date>");
            return;
        }
        presenter.addPerson(tokens[1], tokens[2]);
    }

    private void handleSortCommand(String[] tokens) {
        if (tokens.length < 2) {
            showError("Usage: sort <name|birthdate>");
            return;
        }

        String criterion = tokens[1];
        switch (criterion) {
            case "name":
                presenter.sortPersonsByName();
                break;
            case "birthdate":
                presenter.sortPersonsByBirthDate();
                break;
            default:
                showError("Unknown sort criterion. Use 'name' or 'birthdate'.");
        }
    }

    private void handleSaveCommand(String[] tokens) {
        if (tokens.length < 2) {
            showError("Usage: save <file_path>");
            return;
        }
        presenter.saveFamilyTree(tokens[1]);
    }

    private void handleLoadCommand(String[] tokens) {
        if (tokens.length < 2) {
            showError("Usage: load <file_path>");
            return;
        }
        presenter.loadFamilyTree(tokens[1]);
    }

    @Override
    public void showPersons(String persons) {
        System.out.println(persons);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showError(String error) {
        System.err.println(error);
    }

    public static void main(String[] args) {
        ConsoleFamilyTreeView view = new ConsoleFamilyTreeView();
        FamilyTreePresenter presenter = new FamilyTreePresenterImpl(view, new FamilyTreeFileHandler<>());
        view.setPresenter(presenter);
        view.start();
    }
}