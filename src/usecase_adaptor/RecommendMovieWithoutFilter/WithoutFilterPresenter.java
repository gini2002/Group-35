package error_cases.RecommendMovieWithoutFilter;

import usecase_adaptor.ViewManagerModel;

public class WithoutFilterPresenter implements WithoutFilterOutputBoundary {
    private final WithoutFilterViewModel withoutFilterViewModel;

    private final WithoutFilterResultViewModel withoutFilterResultViewModel;
    private ViewManagerModel viewManagerModel;

    public WithoutFilterPresenter(ViewManagerModel viewManagerModel, WithoutFilterViewModel withoutFilterViewModel, WithoutFilterResultViewModel withoutFilterResultViewModel) {
        this.withoutFilterViewModel = withoutFilterViewModel;
        this.viewManagerModel = viewManagerModel;
        this.withoutFilterResultViewModel = withoutFilterResultViewModel;
    }

    @Override
    public void WithoutFilterSuccessView(WithoutFilterOutputData outputData) {
        withoutFilterViewModel.setWithoutFilterMovies(outputData.getWithoutFilterMovies());
        withoutFilterViewModel.firePropertyChanged();
        withoutFilterResultViewModel.setWithoutFilterMovies(outputData.getWithoutFilterMovies());
        withoutFilterResultViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(withoutFilterViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void WithoutFilterFailView(String error) {
        withoutFilterViewModel.setError(error);
        withoutFilterViewModel.firePropertyChanged();

    }

}