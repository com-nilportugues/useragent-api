/**
 * Copyright 2012 Twitter, Inc
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nilportugues.useragent.app.modules.context.useragent.parser;

import java.util.Map;
import java.util.Objects;

public class OS {
    private final String family, major, minor, patch, patchMinor;

    public OS(String family, String major, String minor, String patch, String patchMinor) {
        this.family = family;
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.patchMinor = patchMinor;
    }

    public static OS fromMap(Map<String, String> m) {
        return new OS(m.get("family"), m.get("major"), m.get("minor"), m.get("patch"), m.get("patch_minor"));
    }

    public String getFamily() {
        return family;
    }

    public String getMajor() {
        return major;
    }

    public String getMinor() {
        return minor;
    }

    public String getPatch() {
        return patch;
    }

    public String getPatchMinor() {
        return patchMinor;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (!(other instanceof OS))
            return false;

        OS o = (OS) other;
        return ((this.family != null && this.family.equals(o.family)) || Objects.equals(this.family, o.family)) &&
            ((this.major != null && this.major.equals(o.major)) || Objects.equals(this.major, o.major)) &&
            ((this.minor != null && this.minor.equals(o.minor)) || Objects.equals(this.minor, o.minor)) &&
            ((this.patch != null && this.patch.equals(o.patch)) || Objects.equals(this.patch, o.patch)) &&
            ((this.patchMinor != null && this.patchMinor.equals(o.patchMinor)) || Objects.equals(this.patchMinor, o.patchMinor));
    }

    @Override
    public int hashCode() {
        int h = family == null ? 0 : family.hashCode();
        h += major == null ? 0 : major.hashCode();
        h += minor == null ? 0 : minor.hashCode();
        h += patch == null ? 0 : patch.hashCode();
        h += patchMinor == null ? 0 : patchMinor.hashCode();
        return h;
    }

    @Override
    public String toString() {
        return String.format("{\"family\": %s, \"major\": %s, \"minor\": %s, \"patch\": %s, \"patch_minor\": %s}",
            family == null ? Constants.EMPTY_STRING : '"' + family + '"',
            major == null ? Constants.EMPTY_STRING : '"' + major + '"',
            minor == null ? Constants.EMPTY_STRING : '"' + minor + '"',
            patch == null ? Constants.EMPTY_STRING : '"' + patch + '"',
            patchMinor == null ? Constants.EMPTY_STRING : '"' + patchMinor + '"');
    }
}
