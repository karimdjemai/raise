import { Module } from 'vuex';

export const translationStore: Module<any, any> = {
  state: {
    currentLanguage: localStorage.getItem('currentLanguage') || 'en',
    languages: {
      nl: { name: 'Nederlands' },
      en: { name: 'English' },
      fr: { name: 'Français' },
      de: { name: 'Deutsch' },
      ro: { name: 'Română' }
      // jhipster-needle-i18n-language-key-pipe - JHipster will add/remove languages in this object
    }
  },
  getters: {
    currentLanguage: state => state.currentLanguage,
    languages: state => state.languages
  },
  mutations: {
    currentLanguage(state, newLanguage) {
      state.currentLanguage = newLanguage;
      localStorage.setItem('currentLanguage', newLanguage);
    }
  }
};
