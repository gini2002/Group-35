package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;

import usecase_adaptor.MovieSearchByKeyword.MovieResultViewModel;
import usecase_adaptor.SearchList.SearchListViewModel;
import usecase_adaptor.MovieSearchByKeyword.SearchByNameViewModel;
import usecase_adaptor.ViewManagerModel;


public class SearchListView extends JPanel implements PropertyChangeListener {

    public final String viewName = "search_list";
    //    private final SearchByNameViewModel searchByNameViewModel;
    private final SearchListViewModel searchListViewModel;

    public DefaultListModel<String> listModel;
    public JList<String> searchList;

    final JButton mainMenuBtn;
    private final ViewManagerModel viewManagerModel;

    private JLabel errorLabel;


    public SearchListView(SearchListViewModel searchListViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.searchListViewModel = searchListViewModel;
        this.searchListViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        JPanel buttons = new JPanel(new FlowLayout());
        mainMenuBtn = new JButton(searchListViewModel.MAIN_MENU_LABEL);
        buttons.add(mainMenuBtn);

        mainMenuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(mainMenuBtn)) {
                    viewManagerModel.setActiveView("movie_recommendation");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("Searched Movies So Far");
        titlePanel.add(title);

        JPanel searchListPanel = new JPanel(new BorderLayout());
        listModel = new DefaultListModel<>();
        searchList = new JList<>(listModel);
        String[] searched = searchListViewModel.getSearchList();

        if (searchList != null) {
            listModel.addAll(Arrays.asList(searched));
        }

        // Update error label
        assert searchList != null;
        searchList.setModel(listModel);

        // Print the contents of listModel for debugging
        System.out.println("Contents of listModel in SEARCH LIST:");
        for (int i = 0; i < listModel.size(); i++) {
            System.out.println(listModel.getElementAt(i));
        }

        JScrollPane scrollPane = new JScrollPane(searchList);

        searchListPanel.add(scrollPane, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(searchListPanel, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);

        String error = searchListViewModel.getError();


        // Error label
        errorLabel = new JLabel(error);
//        this.add(errorLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "searchList":
                updateView();
                break;
            case "error":
                // Handle error property change if needed
                break;
            // Add more cases if there are other properties to handle
        }
    }

    public void updateView() {
        SwingUtilities.invokeLater(() -> {

            String[] searched = searchListViewModel.getSearchList();
            System.out.println("Recommended movies: " + Arrays.toString(searched));

            if (searched != null) {
                listModel.clear();
                listModel.addAll(Arrays.asList(searched));
                searchList.setModel(listModel);

                // Print the contents of listModel for debugging
                System.out.println("Contents of listModel in SEARCH LIST:");
                for (int i = 0; i < listModel.size(); i++) {
                    System.out.println(listModel.getElementAt(i));
                }

                // Update error label
                errorLabel.setText(searchListViewModel.getError());

                // Repaint the UI
                revalidate();
                repaint();
            } else {
                System.out.println("Recommended movies is null.");
            }

            System.out.println("Update complete.");
        });
    }
}


