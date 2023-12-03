package view;

import entity.Movie;
import usecase_adaptor.AddToWatchlist.AddToWatchlistController;
import usecase_adaptor.AddToWatchlist.AddToWatchlistState;
import usecase_adaptor.AddToWatchlist.AddToWatchlistViewModel;
import usecase_adaptor.DeleteWatchlist.DeleteWatchlistController;
import usecase_adaptor.DeleteWatchlist.DeleteWatchlistViewModel;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieState;
import usecase_adaptor.GetDetailOfMovie.GetDetailMovieViewModel;
import usecase_adaptor.GetWatchlist.GetWatchListViewmodel;
import usecase_adaptor.MainMenu.MainMenuViewModel;
import usecase_adaptor.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class GetDetailMovieView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewname = "Details";
    private final GetDetailMovieViewModel getDetailMovieViewModel;
    private final AddToWatchlistController addToWatchlistController;

    private final DeleteWatchlistController deleteWatchlistController;
    JLabel movie_title = new JLabel();

    JLabel overview = new JLabel();

    JLabel genre = new JLabel();

    JButton backToMainMenu;
  
    private final AddToWatchlistViewModel addToWatchlistViewModel;

    private final DeleteWatchlistViewModel deleteWatchlistViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MainMenuViewModel mainMenuViewModel;


    public GetDetailMovieView(GetDetailMovieViewModel getDetailMovieViewmodel,
                              AddToWatchlistController addToWatchlistcontroller,
                              AddToWatchlistViewModel addToWatchlistViewmodel,
                              DeleteWatchlistController deleteWatchlistController,
                              DeleteWatchlistViewModel deleteWatchlistViewModel,
                              ViewManagerModel viewManagerModel,
                              MainMenuViewModel mainMenuViewModel){
        this.getDetailMovieViewModel = getDetailMovieViewmodel;
        this.addToWatchlistController = addToWatchlistcontroller;
        this.addToWatchlistViewModel = addToWatchlistViewmodel;
        this.deleteWatchlistController = deleteWatchlistController;
        this.deleteWatchlistViewModel = deleteWatchlistViewModel;
        this.addToWatchlistViewModel.addPropertyChangeListener(this);
        this.getDetailMovieViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;
        this.mainMenuViewModel = mainMenuViewModel;


        JLabel title = new JLabel("Details");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();

        JButton addToWatchlist = new JButton(AddToWatchlistViewModel.ADD_WATCH_LIST_BUTTON_LABEL);
        JButton removeFromWatchlist = new JButton(DeleteWatchlistViewModel.DELETE_WATCHLIST_BUTTON_LABEL);
        buttons.add(addToWatchlist);
        buttons.add(removeFromWatchlist);
        backToMainMenu = new JButton(GetWatchListViewmodel.MAIN_MENU_BUTTON_LABEL);
        buttons.add(backToMainMenu);
        backToMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(backToMainMenu)) {
                    viewManagerModel.setActiveView(mainMenuViewModel.getViewName());
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        addToWatchlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(addToWatchlist)) {
                    int id = getDetailMovieViewModel.getId();
                    String name = getDetailMovieViewModel.getTitle();
                    String loggedinusername = getDetailMovieViewModel.getLoggedinusername();
                    addToWatchlistController.execute(new Movie(name, id), loggedinusername);
                }
            }
        });
        removeFromWatchlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(removeFromWatchlist)) {
                    int id = getDetailMovieViewModel.getId();
                    String name = getDetailMovieViewModel.getTitle();
                    String loggedinusername = getDetailMovieViewModel.getLoggedinusername();
                    deleteWatchlistController.execute(new Movie(name, id), loggedinusername);
                    // TODO: fulfill the remove from watchlist button
                }
            }
        });


        //SwingUtilities.invokeLater(() -> {
        //            JFrame frame = new JFrame(getDetailMovieViewModel.getTitle());
        //            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //            ImageIcon imageIcon = new ImageIcon(getDetailMovieViewModel.getPoster_path());
        //            JLabel label = new JLabel(imageIcon);
        //            frame.add(label);
        //            frame.setSize(400, 300);
        //            frame.setLocationRelativeTo(null);
        //            frame.setVisible(true);
        //            this.add(frame);
        //        });


        //movie_title.setText(getDetailMovieViewModel.getTitle());
        //        overview.setText(getDetailMovieViewModel.getOverview());
        //        // List<String> genre_list = getDetailMovieViewModel.getGenre();
        //        String genre_text = "";
        //        if (getDetailMovieViewModel.getGenre() != null) {
        //            List<String> genre_list = getDetailMovieViewModel.getGenre();
        //            for (int i = 0; i < genre_list.size(); i++){
        //                genre_text = genre_text + "," + genre_list.get(i);
        //            }
        //        }
        //        genre.setText(genre_text);
        //        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //        this.add(title);
        //        this.add(buttons);
        //        this.add(movie_title);
        //        this.add(overview);
        //        this.add(genre);
    }

    public void updateView() {
        SwingUtilities.invokeLater(() -> {
            if (overview != null) {this.removeAll();}
            JLabel title = new JLabel("details");
            title.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel buttons = new JPanel();

            JButton addToWatchlist = new JButton(AddToWatchlistViewModel.ADD_WATCH_LIST_BUTTON_LABEL);
            JButton removeFromWatchlist = new JButton(DeleteWatchlistViewModel.DELETE_WATCHLIST_BUTTON_LABEL);
            buttons.add(addToWatchlist);
            buttons.add(removeFromWatchlist);
            backToMainMenu = new JButton(GetWatchListViewmodel.MAIN_MENU_BUTTON_LABEL);
            buttons.add(backToMainMenu);
            backToMainMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(backToMainMenu)) {
                        viewManagerModel.setActiveView(mainMenuViewModel.getViewName());
                        viewManagerModel.firePropertyChanged();
                    }
                }
            });

            addToWatchlist.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(addToWatchlist)) {
                        int id = getDetailMovieViewModel.getId();
                        String name = getDetailMovieViewModel.getTitle();
                        String loggedinusername = getDetailMovieViewModel.getLoggedinusername();
                        addToWatchlistController.execute(new Movie(name, id), loggedinusername);
                    }
                }
            });
            removeFromWatchlist.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(removeFromWatchlist)) {
                        int id = getDetailMovieViewModel.getId();
                        String name = getDetailMovieViewModel.getTitle();
                        String loggedinusername = getDetailMovieViewModel.getLoggedinusername();
                        deleteWatchlistController.execute(new Movie(name, id), loggedinusername);
                        // TODO: fulfill the remove from watchlist button
                    }
                }
            });
            //SwingUtilities.invokeLater(() -> {
            //                JTextArea frame = new JFrame(getDetailMovieViewModel.getTitle());
            //                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //                ImageIcon imageIcon = new ImageIcon(getDetailMovieViewModel.getPoster_path());
            //                JLabel label = new JLabel(imageIcon);
            //                frame.add(label);
            //                frame.setSize(400, 300);
            //                frame.setLocationRelativeTo(null);
            //                frame.setVisible(true);
            //                this.add(frame);
            //            });
            Font font20 = new Font("Arial", Font.PLAIN, 20);
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Overview");

                JTextArea descriptionArea = new JTextArea();
                descriptionArea.setText(getDetailMovieViewModel.getOverview());
                descriptionArea.setEditable(false);
                descriptionArea.setLineWrap(true);
                descriptionArea.setWrapStyleWord(true);
                descriptionArea.setFont(font20);
                descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);
                //JScrollPane scrollPane = new JScrollPane(descriptionArea);
                ImageIcon imageIcon = new ImageIcon("https://image.tmdb.org/t/p/w1280" +
                        getDetailMovieViewModel.getPoster_path());
                System.out.println("https://image.tmdb.org/t/p/w1280" + getDetailMovieViewModel.getPoster_path());
                JLabel poster = new JLabel();
                poster.setIcon(imageIcon);
                poster.setVisible(true);
                this.add(descriptionArea);
                this.add(poster);
            });

            movie_title.setText(getDetailMovieViewModel.getTitle());
            Font font70 = new Font("Arial", Font.PLAIN, 70);
            movie_title.setFont(font70);
            // overview.setText(getDetailMovieViewModel.getOverview());
            // List<String> genre_list = getDetailMovieViewModel.getGenre();
            String genre_text = "Genres:  ";
            if (getDetailMovieViewModel.getGenre() != null) {
                List<String> genre_list = getDetailMovieViewModel.getGenre();
                for (int i = 0; i < genre_list.size(); i++){
                    genre_text = genre_text + "   " + genre_list.get(i);
                }
            }
            genre.setText(genre_text);
            genre.setFont(font20);
            movie_title.setAlignmentX(Component.LEFT_ALIGNMENT);
            genre.setAlignmentX(Component.LEFT_ALIGNMENT);
            title.setFont(font20);
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(title);
            this.add(buttons);
            this.add(movie_title);
            // this.add(overview);
            this.add(genre);
        });
    }

    //@Override

    //public void actionPerformed(ActionEvent e) {System.out.println("Click " + e.getActionCommand());}

    //@Override
    //public void propertyChange(PropertyChangeEvent evt) {
    //    GetDetailMovieState state = (GetDetailMovieState) evt.getNewValue();
    //    movie_title.setText(state.getTitle());
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof GetDetailMovieState){
            GetDetailMovieState getDetailMovieState = (GetDetailMovieState) evt.getNewValue();
            if (getDetailMovieState.getError() != null){
                JOptionPane.showMessageDialog(this, getDetailMovieState.getError());
            } else {
                switch (evt.getPropertyName()) {
                    case "state":
                        updateView();
                        break;
                    case "error":
                        break;
                }
            }
        }
        else if (evt.getNewValue() instanceof AddToWatchlistState) {
            AddToWatchlistState state = (AddToWatchlistState) evt.getNewValue();
            if (state.getMovieExistError() != null) {
                JOptionPane.showMessageDialog(this, state.getMovieExistError());
            } else {
                JOptionPane.showMessageDialog(this, state.getMessage());
            }
        }
    }
}
